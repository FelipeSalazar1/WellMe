CREATE TABLE pacientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100),
    telefone VARCHAR(15),
    data_nascimento DATE NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    endereco VARCHAR(200),
    observacoes TEXT,
    data_cadastro TIMESTAMP NOT NULL
);

CREATE TABLE consultas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    tipo_consulta VARCHAR(200),
    medico VARCHAR(100),
    observacoes TEXT,
    status VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE TABLE exames (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    tipo_exame VARCHAR(100) NOT NULL,
    descricao VARCHAR(200),
    data_exame DATE NOT NULL,
    laboratorio VARCHAR(100),
    resultado TEXT,
    status VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE TABLE lembretes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT,
    data_lembrete TIMESTAMP NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE INDEX idx_consultas_paciente ON consultas(paciente_id);
CREATE INDEX idx_consultas_data ON consultas(data_hora);
CREATE INDEX idx_exames_paciente ON exames(paciente_id);
CREATE INDEX idx_exames_data ON exames(data_exame);
CREATE INDEX idx_lembretes_paciente ON lembretes(paciente_id);
CREATE INDEX idx_lembretes_data ON lembretes(data_lembrete);
CREATE INDEX idx_lembretes_status ON lembretes(status);

