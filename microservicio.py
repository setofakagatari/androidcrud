import mysql.connector
from flask import Flask

app= Flask(__name__)
mydb= mysql.connector.connect(user="root",password="",host="localhost",database="secuitymaps")
Cursor = mydb.cursor()

@app.route("/api/ConsultarUsuarios/")
def ConsultarUsuarios():
    Cursor.callproc("mostrarUsuarios",())
    for result in Cursor.stored_results():
        c=result.fetchall()
    return(c)

@app.route("/api/ConsultarUsuario/<id_user>")
def ConsultarUsuari(id_user):
    id_user=int(id_user)
    Cursor.callproc("mostrarUsuario",(id_user,))
    for result in Cursor.stored_results():
        c=result.fetchall()
    return(c)
try:
    @app.route("/api/InsertarUsuario/<username>/<fullname>/<email>/<password>")
    def ConsultarUsuario(username,fullname,email,password):
        datos=(username,fullname,email,password)
        Cursor.callproc("insertarUsuario",datos)
        mydb.commit()
        return ("usuario insertado correctamente")

except mysql.connector.Error as error:
    mydb.rollback()
    print(f"Error al insertar usuario: {error}")

try:
    @app.route("/api/ActualizarUsuario/<user_id>/<username>/<fullname>/<email>/<password>")
    def ActualizarUsuario(user_id,username,fullname,email,password):
        user_id=int(user_id)
        datos=(user_id,username,fullname,email,password)
        Cursor.callproc('actualizarUsuario', (datos))

        # Confirmar la transacción
        mydb.commit()
        return("Usuario actualizado correctamente.")

except mysql.connector.Error as error:
        # Si hay un error, revertir la transacción
    mydb.rollback()
    print(f"Error al actualizar usuario: {error}")

try:
    @app.route("/api/EliminarUsuario/<user_id>")
    def EliminarUsuarios(user_id):
        user_id=int(user_id)
        Cursor.callproc('eliminarUsuario', (user_id,))

        # Confirmar la transacción
        mydb.commit()
        return("Usuario eliminado correctamente.")

except mysql.connector.Error as error:
        # Si hay un error, revertir la transacción
    mydb.rollback()
    print(f"Error al eliminar usuario: {error}")

# Función para insertar un nuevo registro en la tabla 'mark'
@app.route("/api/InsertarMark/<cordsx>/<cordsy>/<int:id_user>")
def insertar_mark_por_link(cordsx, cordsy, id_user):
    try:
        Cursor.callproc("InsertMark", (cordsx, cordsy, id_user))
        mydb.commit("marca insertada")
        return ()
    except mysql.connector.Error as error:
        mydb.rollback()
        return (error)

# Función para obtener todos los registros de la tabla 'mark'
@app.route("/api/ConsultarMarks")
def consultar_marks():
    try:
        Cursor.callproc("GetAllMarks")
        marks = Cursor.fetchall()
        return (marks)
    except mysql.connector.Error as error:
        return (error)
    


# Función para obtener un registro por su ID de la tabla 'mark'
@app.route("/api/ConsultarMark/<int:id_mark>")
def consultar_mark_por_id(id_mark):
    try:
        Cursor.callproc("GetMarkById", (id_mark,))
        mark = Cursor.fetchone()
        if mark:
            return ( mark)
        else:
            return ("no se encontro")
    except mysql.connector.Error as error:
        return (error)

# Función para actualizar un registro en la tabla 'mark'
@app.route("/api/ActualizarMark/<int:id_mark>/<cordsx>/<cordsy>/<int:id_user>")
def actualizar_mark_por_id(id_mark, cordsx, cordsy, id_user):
    try:
        Cursor.callproc("UpdateMark", (id_mark, cordsx, cordsy, id_user))
        mydb.commit()
        return ("Mark actualizado correctamente")
    except mysql.connector.Error as error:
        mydb.rollback()
        return ("Error al actualizar mark")

# Función para eliminar un registro de la tabla 'mark' por su ID
@app.route("/api/EliminarMark/<int:id_mark>")
def eliminar_mark_por_id(id_mark):
    try:
        Cursor.callproc("DeleteMark", (id_mark,))
        mydb.commit()
        return ( "Mark eliminado correctamente")
    except mysql.connector.Error as error:
        mydb.rollback()
        return ("Error al eliminar mark")


if __name__=="__main__":
    print(app.url_map)
    app.run(debug=True)
