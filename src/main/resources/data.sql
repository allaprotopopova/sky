DELETE FROM collocations;
DELETE FROM words_groups;
ALTER SEQUENCE global_seq_cliff RESTART WITH 100000;

INSERT INTO words_groups (id,name) VALUES
                                       (1,'injury'),
                                       (2,'random');

INSERT INTO collocations (group_id, mainword, pairword) VALUES
(1,'sustained', 'injury'),
(1,'to get', 'a rash'),
(1,'to receive', 'treatment'),
(2,'to relieve', 'stiffness'),
(2,'to prescribe', 'painkillers'),
(2,'to have', 'a stroke')