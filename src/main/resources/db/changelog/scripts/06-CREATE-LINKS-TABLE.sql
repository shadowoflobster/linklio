CREATE SEQUENCE linklio.links_seq
    INCREMENT 1
    START 1000;

CREATE TABLE linklio.icons (
    id VARCHAR(50) PRIMARY KEY,
    icon_url TEXT NOT NULL,
    description TEXT
);

CREATE TABLE linklio.links(
    ID BIGINT PRIMARY KEY DEFAULT nextval('linklio.links_seq'),
    link_text VARCHAR(255) NOT NULL,
    url TEXT NOT NULL,
    user_id BIGINT,

    bg_color VARCHAR(7) DEFAULT '#FFFFFF',
    text_color VARCHAR(7) DEFAULT '#000000',
    border_color VARCHAR(7) DEFAULT '#cccccc',

    hover_bg_color VARCHAR(7) DEFAULT '#eeeeee',
    hover_border_color VARCHAR(7) DEFAULT '#bbbbbb',

    font_style VARCHAR(20) DEFAULT 'normal',
    icon_id VARCHAR(50),

    is_visible BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),

    CONSTRAINT fk_links_user FOREIGN KEY (user_id) REFERENCES linklio.users(id),
    CONSTRAINT fk_links_icon FOREIGN KEY (icon_id) REFERENCES linklio.icons(id)

);