ALTER TABLE flight
    ADD COLUMN members JSON;
    
UPDATE flight f
SET f.members =
        (SELECT JSON_ARRAYAGG(crew_member_id) AS members FROM flight_crew fc WHERE f.id = fc.crew_member_id);

ALTER TABLE plane
    ADD COLUMN review_details JSON;
    
UPDATE plane p
SET review_details = (SELECT JSON_ARRAYAGG(JSON_OBJECT(
	'id', r.id,
    'end_date', r.end_date,
    'start_date', r.start_date,
    'review_duration', r.review_duration,
    'mechanic_id', r.mechanic_id,
    'review_type', r.review_type,
    'description', r.description,
    'airport_id', r.airport_id))
FROM review r WHERE r.plane_id = p.id);