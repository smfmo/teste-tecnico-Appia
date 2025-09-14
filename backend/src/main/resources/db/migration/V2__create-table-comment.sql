CREATE TABLE COMMENT (
    id UUID PRIMARY KEY,
    incident_id UUID NOT NULl,
    author VARCHAR(120) NOT NULL,
    message TEXT NOT NULL,
    created_date TIMESTAMP NOT NULL,

    CONSTRAINT fk_comment_incident FOREIGN KEY (incident_id) REFERENCES incident(id) ON DELETE CASCADE
);