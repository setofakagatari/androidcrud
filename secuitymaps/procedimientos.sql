DELIMITER //

CREATE PROCEDURE mostrarUsuarios()
BEGIN
    SELECT * FROM users;
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE actualizarUsuario(
    IN p_id_user INT,
    IN p_username VARCHAR(255),
    IN p_fullname VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_password VARCHAR(255)
)
BEGIN
    UPDATE users 
    SET username = p_username,
        fullname = p_fullname,
        email = p_email,
        password = p_password
    WHERE id_user = p_id_user;
END //

DELIMITER ;
DELIMITER //

DELIMITER //

CREATE PROCEDURE mostrarUsuario(p_id_user integer)

BEGIN
    SELECT * FROM users WHERE(id_user=p_id_user);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE insertarUsuario(
    IN p_username VARCHAR(255),
    IN p_fullname VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_password VARCHAR(255)
)
BEGIN
    INSERT INTO users (username, fullname, email, password)
    VALUES (p_username, p_fullname, p_email, p_password);
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE eliminarUsuario(
    IN p_id_user INT
)
BEGIN
    DELETE FROM users WHERE id_user = p_id_user;
END //

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `InsertMark`(
    IN p_cordsx VARCHAR(10000),
    IN p_cordsy VARCHAR(10000),
    IN p_id_user INT
)
BEGIN
    INSERT INTO `mark` (`cordsx`, `cordsy`, `id_user`)
    VALUES (p_cordsx, p_cordsy, p_id_user);
END$$
DELIMITER ;

-- PROCEDIMIENTO PARA OBTENER TODOS LOS REGISTROS
DELIMITER $$
CREATE PROCEDURE `GetAllMarks`()
BEGIN
    SELECT * FROM `mark`;
END$$
DELIMITER ;

-- PROCEDIMIENTO PARA OBTENER UN REGISTRO POR SU ID
DELIMITER $$
CREATE PROCEDURE `GetMarkById`(
    IN p_id_mark INT
)
BEGIN
    SELECT * FROM `mark` WHERE `id_mark` = p_id_mark;
END$$
DELIMITER ;

-- PROCEDIMIENTO PARA ACTUALIZAR UN REGISTRO
DELIMITER $$
CREATE PROCEDURE `UpdateMark`(
    IN p_id_mark INT,
    IN p_cordsx VARCHAR(10000),
    IN p_cordsy VARCHAR(10000),
    IN p_id_user INT
)
BEGIN
    UPDATE `mark`
    SET `cordsx` = p_cordsx,
        `cordsy` = p_cordsy,
        `id_user` = p_id_user
    WHERE `id_mark` = p_id_mark;
END$$
DELIMITER ;

-- PROCEDIMIENTO PARA ELIMINAR UN REGISTRO
DELIMITER $$
CREATE PROCEDURE `DeleteMark`(
    IN p_id_mark INT
)
BEGIN
    DELETE FROM `mark` WHERE `id_mark` = p_id_mark;
END$$
DELIMITER ;
