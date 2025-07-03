CREATE TABLE linklio.roles (
    id BIGINT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE linklio.user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES linklio.users(id),
    FOREIGN KEY (role_id) REFERENCES linklio.roles(id)
);


INSERT INTO linklio.roles (id, role_name)
VALUES (0,'USER'),
       (1,'MODERATOR'),
       (2,'ADMIN');