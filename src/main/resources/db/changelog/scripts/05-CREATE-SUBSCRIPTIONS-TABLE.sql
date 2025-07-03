CREATE SEQUENCE linklio.subscriptions_seq
    INCREMENT 1
    START 1000;

CREATE TABLE linklio.subscriptions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    plan_id BIGINT NOT NULL,
    start_date DATE NOT NULL DEFAULT CURRENT_DATE,
    end_date DATE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    FOREIGN KEY (user_id) REFERENCES linklio.users(id),
    FOREIGN KEY (plan_id) REFERENCES linklio.plans(id)
);

