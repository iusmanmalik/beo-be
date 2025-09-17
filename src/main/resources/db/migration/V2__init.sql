DROP TABLE form_schema;
DROP TABLE submission;
CREATE TABLE submission (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
        event_id VARCHAR(255) NOT NULL,
        event_name VARCHAR(255),
        guest_count INTEGER,
        form_date TIMESTAMP WITH TIME ZONE,
        event_starting_time TIME,
        event_ending_time TIME,
        event_purpose TEXT,
        event_location TEXT,
        client_name VARCHAR(255),
        company_name VARCHAR(255),
        phone_number VARCHAR(50),
        email VARCHAR(255),
        venue_room VARCHAR(255),
        setup_style VARCHAR(255),
        serving_time TIME,
        dietary_requirements_allergies TEXT,
        special_instructions TEXT,

    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    deleted_at TIMESTAMP WITH TIME ZONE
);


-- Create menu_items table
CREATE TABLE menu_items (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT,
    submission_id UUID NOT NULL,
    CONSTRAINT fk_event
        FOREIGN KEY(submission_id)
        REFERENCES submission(id)
        ON DELETE CASCADE
);

CREATE TABLE beverages (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT,
    submission_id UUID NOT NULL,
    CONSTRAINT fk_event
        FOREIGN KEY(submission_id)
        REFERENCES submission(id)
        ON DELETE CASCADE
);

CREATE TABLE audio_visual_equipments (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT,
    submission_id UUID NOT NULL,
    CONSTRAINT fk_event
        FOREIGN KEY(submission_id)
        REFERENCES submission(id)
        ON DELETE CASCADE
);