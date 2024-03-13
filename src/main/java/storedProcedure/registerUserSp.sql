CREATE OR REPLACE PROCEDURE register_user(
    id_user_param bigint,
    id_card_param VARCHAR(10),
    name_param VARCHAR(50),
    lastname_param VARCHAR(50),
    birthdate_param DATE,
    username_param VARCHAR(50),
    mail_param VARCHAR(150),
    password_param VARCHAR(255),
    active_session_param VARCHAR(1),
    status_param VARCHAR(20),
    failed_attempts_param INT,
    roles_param INT[]
)
LANGUAGE plpgsql
AS $$
DECLARE
    user_id BIGINT;
    role_id INT;
BEGIN
    -- Insert user
    INSERT INTO users (id_user, id_card, name, lastname, birthdate, username, mail, password, active_session, status, failed_attempts)
    VALUES (id_user_param, id_card_param, name_param, lastname_param, birthdate_param, username_param, mail_param, password_param, active_session_param, status_param, failed_attempts_param)
    RETURNING id_user INTO user_id;

    -- Insert roles
    FOREACH role_id IN ARRAY roles_param
    LOOP
        INSERT INTO user_roles (id_user, id_role)
        VALUES (user_id, role_id);
    END LOOP;

    COMMIT;
END;
$$;


-- Execute
CALL register_user(
  	1,
    '1234567890', 
    'John',       
    'Doe',       
    '2000-01-01', 
    'johndoe',   
    'johndoe@example.com', 
    'password123', 
    '1',          
    '1',     
    0,           
    ARRAY[1]
);