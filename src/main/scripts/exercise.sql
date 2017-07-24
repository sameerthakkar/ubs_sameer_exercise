-- create table containing list of events

CREATE TABLE EVENTS (
	EVENT_ID NUMBER,
	EVENT_NAME VARCHAR2(20),
	EVENT_TIME TIMESTAMP
);

-- prepare data

INSERT INTO EVENTS VALUES (1, 'EventB', TO_TIMESTAMP('07/18/2017 21:00:00', 'MM/DD/YYYY HH24:MI:SS'));
INSERT INTO EVENTS VALUES (2, 'EventA', TO_TIMESTAMP('07/18/2017 21:00:00', 'MM/DD/YYYY HH24:MI:SS'));
INSERT INTO EVENTS VALUES (3, 'EventA', TO_TIMESTAMP('07/19/2017 21:00:00', 'MM/DD/YYYY HH24:MI:SS'));
INSERT INTO EVENTS VALUES (4, 'EventB', TO_TIMESTAMP('07/19/2017 23:00:00', 'MM/DD/YYYY HH24:MI:SS'));
INSERT INTO EVENTS VALUES (5, 'EventA', TO_TIMESTAMP('07/19/2017 23:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

--sql query to identify all unique events with latest time when it happened

-- case 1: using aggregation functions -  preffered in this case

SELECT
	EVENT_NAME,
	TO_CHAR(MAX(EVENT_TIME), 'MM/DD/YYYY HH24:MI:SS') LATEST_EVENT_TIME
FROM
	EVENTS
GROUP BY
	EVENT_NAME
ORDER BY
	EVENT_NAME;
 
 
-- case 2: using analytical functions

SELECT EVENT_NAME, TO_CHAR(EVENT_TIME, 'MM/DD/YYYY HH24:MI:SS') LATEST_EVENT_TIME
FROM
	(
	SELECT 
		EVENT_NAME,
		EVENT_TIME,
		ROW_NUMBER() OVER (PARTITION BY EVENT_NAME ORDER BY EVENT_TIME DESC) RNK
	FROM 
		EVENTS
	)
WHERE 
	RNK = 1;	
 
 --rollback
 -- DELETE FROM EVENTS WHERE EVENT_ID IN (1, 2, 3, 4, 5);
 -- COMMIT;
 -- DROP TABLE EVENTS;