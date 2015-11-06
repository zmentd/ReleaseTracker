--
-- This file was generated on 11/03/2015 at 06:45:22 AM.
-- It contains HsqlDb version 1.8.0.8 compatible SQL statements.
--

INSERT INTO RT_WORKFLOW_STATUS_COMPLETION( ID, STATUS_ID, PHASE_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_IN_STATUS, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 1, 5, NULL, '2015-07-08', '2015-07-30', '22', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_STATUS_COMPLETION( ID, STATUS_ID, PHASE_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_IN_STATUS, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 2, 5, NULL, '2015-07-16', '2015-07-08', '357', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_STATUS_COMPLETION( ID, STATUS_ID, PHASE_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_IN_STATUS, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 3, 5, NULL, '2015-07-08', '2015-07-08', '0', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_STATUS_COMPLETION( ID, STATUS_ID, PHASE_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_IN_STATUS, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 4, 5, NULL, '2015-07-08', '2015-07-30', '22', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_STATUS_COMPLETION( ID, STATUS_ID, PHASE_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_IN_STATUS, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 5, 5, NULL, '2015-07-16', '2015-07-08', '357', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_STATUS_COMPLETION( ID, STATUS_ID, PHASE_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_IN_STATUS, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 6, 5, NULL, '2015-07-08', '2015-07-08', '0', '42', '2015-11-03 06:45:22.000000');

INSERT INTO RT_WORKFLOW_PHASE_COMPLETION( ID, WORKFLOW_ID, PHASE_ID, PHASE_SEQ, CUR_STATUS_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_FROM_EXPCTD, DAYS_IN_PHASE, EXPCTD_CMPLT_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 100, NULL, 3, 3, 1, '2015-07-08', '2015-07-30', 22, 22, '2015-07-30', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_PHASE_COMPLETION( ID, WORKFLOW_ID, PHASE_ID, PHASE_SEQ, CUR_STATUS_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_FROM_EXPCTD, DAYS_IN_PHASE, EXPCTD_CMPLT_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 101, NULL, 1, 1, 2, '2014-07-16', '2015-07-08', 354, 357, '2015-07-05', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_PHASE_COMPLETION( ID, WORKFLOW_ID, PHASE_ID, PHASE_SEQ, CUR_STATUS_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_FROM_EXPCTD, DAYS_IN_PHASE, EXPCTD_CMPLT_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 102, NULL, 2, 2, 3, '2015-07-08', '2015-07-08', 12, 0, '2015-07-20', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_PHASE_COMPLETION( ID, WORKFLOW_ID, PHASE_ID, PHASE_SEQ, CUR_STATUS_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_FROM_EXPCTD, DAYS_IN_PHASE, EXPCTD_CMPLT_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 103, NULL, 3, 3, 4, '2015-07-08', '2015-07-30', 22, 22, '2015-07-30', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_PHASE_COMPLETION( ID, WORKFLOW_ID, PHASE_ID, PHASE_SEQ, CUR_STATUS_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_FROM_EXPCTD, DAYS_IN_PHASE, EXPCTD_CMPLT_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 104, NULL, 1, 1, 5, '2014-07-16', '2015-07-08', 354, 357, '2015-07-05', '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW_PHASE_COMPLETION( ID, WORKFLOW_ID, PHASE_ID, PHASE_SEQ, CUR_STATUS_CMPLT_ID, ENTRY_DATE, CMPLT_DATE, DAYS_FROM_EXPCTD, DAYS_IN_PHASE, EXPCTD_CMPLT_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 105, NULL, 2, 2, 6, '2015-07-08', '2015-07-08', 12, 0, '2015-07-20', '42', '2015-11-03 06:45:22.000000');

INSERT INTO RT_WORKFLOW( ID, DISC, TEAMIMPACT_ID, ROM, IDEA_ID, REL_ID, CUR_PHASE_CMPLT_ID, TARGT_IMPL_DATE, CANCELED_DATE, PHASE_TARGET_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 100, 'TEAM_IMPACT', 100, 'XS', NULL, NULL, 100, '2015-10-10', NULL, NULL, '42', '2015-11-03 06:45:22.000000');
INSERT INTO RT_WORKFLOW( ID, DISC, TEAMIMPACT_ID, ROM, IDEA_ID, REL_ID, CUR_PHASE_CMPLT_ID, TARGT_IMPL_DATE, CANCELED_DATE, PHASE_TARGET_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) VALUES( 101, 'IDEA', NULL, 'XS', 100, NULL, 103, '2015-10-10', NULL, NULL, '42', '2015-11-03 06:45:22.000000');

UPDATE RT_WORKFLOW_PHASE_COMPLETION SET WORKFLOW_ID=100 WHERE ID=100;
UPDATE RT_WORKFLOW_PHASE_COMPLETION SET WORKFLOW_ID=100 WHERE ID=101;
UPDATE RT_WORKFLOW_PHASE_COMPLETION SET WORKFLOW_ID=100 WHERE ID=102;
UPDATE RT_WORKFLOW_PHASE_COMPLETION SET WORKFLOW_ID=101 WHERE ID=103;
UPDATE RT_WORKFLOW_PHASE_COMPLETION SET WORKFLOW_ID=101 WHERE ID=104;
UPDATE RT_WORKFLOW_PHASE_COMPLETION SET WORKFLOW_ID=101 WHERE ID=105;

UPDATE RT_WORKFLOW_STATUS_COMPLETION SET PHASE_CMPLT_ID=100 WHERE ID=1;
UPDATE RT_WORKFLOW_STATUS_COMPLETION SET PHASE_CMPLT_ID=101 WHERE ID=2;
UPDATE RT_WORKFLOW_STATUS_COMPLETION SET PHASE_CMPLT_ID=102 WHERE ID=3;
UPDATE RT_WORKFLOW_STATUS_COMPLETION SET PHASE_CMPLT_ID=103 WHERE ID=4;
UPDATE RT_WORKFLOW_STATUS_COMPLETION SET PHASE_CMPLT_ID=104 WHERE ID=5;
UPDATE RT_WORKFLOW_STATUS_COMPLETION SET PHASE_CMPLT_ID=105 WHERE ID=6;

UPDATE RT_WORKFLOW SET CUR_PHASE_CMPLT_ID=100 WHERE ID=100;
UPDATE RT_WORKFLOW SET CUR_PHASE_CMPLT_ID=103 WHERE ID=101;

COMMIT WORK;