DROP TABLE IF EXISTS collocations;
DROP TABLE IF EXISTS words_groups;
DROP SEQUENCE IF EXISTS global_seq_cliff;

CREATE SEQUENCE GLOBAL_SEQ_CLIFF START 100000;

CREATE TABLE words_groups
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_cliff'),
  name            VARCHAR           NOT NULL
);
CREATE UNIQUE INDEX words_groups_unique_name_idx
  ON words_groups (name);

CREATE TABLE collocations
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_cliff'),
  group_id       INTEGER  NOT NULL
    constraint collocations_words_groups_id_fk
    references words_groups,
  mainword             VARCHAR            NOT NULL,
  pairword            VARCHAR           NOT NULL
);

CREATE UNIQUE INDEX collocations_unique_gropup_id_mainword_pairword_idx
  ON collocations (group_id,mainword, pairword);