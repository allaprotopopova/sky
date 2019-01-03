DELETE FROM collocations;
DELETE FROM words_groups;
ALTER SEQUENCE global_seq_cliff RESTART WITH 100000;

INSERT INTO words_groups (id,name) VALUES
                                       (1,'New Year Set'),
                                       (2,'Idioms');

INSERT INTO collocations (group_id, mainword, pairword) VALUES
(1,'I wish', 'you'),
(1,'a Merry', 'Christmas'),
(1,'and Happy', 'New Year!'),
(1,'Be as cool', 'as you are!'),
(2,'The best of', 'both worlds'),
(2,'Speak of ', 'the devil'),
(2,'See eye ', 'to eye'),
(2,'Once ', 'in a blue moon'),
(2,'When pigs ', 'fly'),
(2,'To cost an arm ', 'and a leg');