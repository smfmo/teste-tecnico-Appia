CREATE TABLE INCIDENT (
    id UUID PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    description TEXT NULL,
    priority VARCHAR(10) NOT NULL,
    status VARCHAR(15) NOT NULL,
    responsible_email VARCHAR(255) NOT NULL,
    tags TEXT[] NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL,

    CONSTRAINT check_priority CHECK (priority IN ('BAIXA','MEDIA','ALTA')),
    CONSTRAINT check_status CHECK (status IN ('ABERTA','EM_ANDAMENTO','RESOLVIDA','CANCELADA'))
);