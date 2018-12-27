DELETE FROM collocations;
ALTER SEQUENCE global_seq_cliff RESTART WITH 100000;

INSERT INTO collocations (mainword, pairword) VALUES
('sustained', 'injury'),
('to get', 'a rash'),
('to receive', 'treatment'),
('to relieve', 'stiffness'),
('to prescribe', 'painkillers'),
('to have', 'a stroke')