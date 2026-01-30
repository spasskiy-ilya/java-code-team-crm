CREATE TABLE IF NOT EXISTS agents (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS agent_info (
    id BIGSERIAL PRIMARY KEY,
    agent_id BIGINT NOT NULL UNIQUE,
    name VARCHAR(255),
    second_name VARCHAR(255),
    father_name VARCHAR(255),
    birthday_date DATE,
    country VARCHAR(255),
    address VARCHAR(255),
    phone_number VARCHAR(255),
    telegram_nik_name VARCHAR(255),
    email VARCHAR(255),
    portal_login VARCHAR(255),
    portal_password VARCHAR(255),
    discord_login VARCHAR(255),
    registration_date DATE,
    CONSTRAINT fk_agent_info_agent FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS current_status (
    id BIGSERIAL PRIMARY KEY,
    agent_id BIGINT NOT NULL UNIQUE,
    current_stage VARCHAR(255),
    current_sub_stage VARCHAR(255),
    current_stage_start DATE,
    current_sub_stage_start DATE,
    CONSTRAINT fk_current_status_agent FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS agent_history (
    id BIGSERIAL PRIMARY KEY,
    agent_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_agent_history_agent FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS contract_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE,
    signed BOOLEAN,
    sign_date DATE,
    re_sign_date DATE,
    broke_date DATE,
    CONSTRAINT fk_contract_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS education_first_part_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE,
    CONSTRAINT fk_education_first_part_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS education_first_part_modules (
    id BIGSERIAL PRIMARY KEY,
    education_first_part_history_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    stage_name VARCHAR(255),
    CONSTRAINT fk_education_first_part_modules_history FOREIGN KEY (education_first_part_history_id) REFERENCES education_first_part_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS education_second_part_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE,
    CONSTRAINT fk_education_second_part_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS education_second_part_modules (
    id BIGSERIAL PRIMARY KEY,
    education_second_part_history_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    stage_name VARCHAR(255),
    CONSTRAINT fk_education_second_part_modules_history FOREIGN KEY (education_second_part_history_id) REFERENCES education_second_part_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cv_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE,
    CONSTRAINT fk_cv_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cv_modules (
    id BIGSERIAL PRIMARY KEY,
    cv_history_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    stage_name VARCHAR(255),
    CONSTRAINT fk_cv_modules_history FOREIGN KEY (cv_history_id) REFERENCES cv_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS practice_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE,
    CONSTRAINT fk_practice_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS practice_modules (
    id BIGSERIAL PRIMARY KEY,
    practice_history_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    stage_name VARCHAR(255),
    CONSTRAINT fk_practice_modules_history FOREIGN KEY (practice_history_id) REFERENCES practice_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS search_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE,
    CONSTRAINT fk_search_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS work_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE,
    CONSTRAINT fk_work_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS completed_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    completed_date DATE,
    CONSTRAINT fk_completed_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS archive_history (
    id BIGSERIAL PRIMARY KEY,
    agent_history_id BIGINT NOT NULL UNIQUE,
    archive_date DATE,
    reason TEXT,
    CONSTRAINT fk_archive_history_agent_history FOREIGN KEY (agent_history_id) REFERENCES agent_history(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS notes (
    id BIGSERIAL PRIMARY KEY,
    agent_id BIGINT NOT NULL,
    date DATE,
    note TEXT,
    CONSTRAINT fk_notes_agent FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS daily_notes (
    id BIGSERIAL PRIMARY KEY,
    agent_id BIGINT NOT NULL,
    date DATE,
    note TEXT,
    CONSTRAINT fk_daily_notes_agent FOREIGN KEY (agent_id) REFERENCES agents(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_agent_info_agent_id ON agent_info(agent_id);
CREATE INDEX IF NOT EXISTS idx_current_status_agent_id ON current_status(agent_id);
CREATE INDEX IF NOT EXISTS idx_agent_history_agent_id ON agent_history(agent_id);
CREATE INDEX IF NOT EXISTS idx_notes_agent_id ON notes(agent_id);
CREATE INDEX IF NOT EXISTS idx_daily_notes_agent_id ON daily_notes(agent_id);
CREATE INDEX IF NOT EXISTS idx_daily_notes_date ON daily_notes(date);
