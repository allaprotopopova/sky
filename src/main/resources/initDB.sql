DROP TABLE IF EXISTS collocations;
DROP SEQUENCE IF EXISTS global_seq_cliff ;

CREATE SEQUENCE GLOBAL_SEQ_CLIFF START 100000;

CREATE TABLE collocations
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_cliff'),
  mainword             VARCHAR            NOT NULL,
  pairword            VARCHAR           NOT NULL
);

CREATE UNIQUE INDEX collocations_unique_mainword_pairword_idx
  ON collocations (mainword, pairword)