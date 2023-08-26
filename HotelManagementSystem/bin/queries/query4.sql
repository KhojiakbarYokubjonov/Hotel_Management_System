-- Print the average ratings of different amenities recorded within the range of two dates(input by the user)
-- and sort them in descending order.
SELECT a.name AS AMENITY, AVG(rating.score) AS RATING
FROM TAOSEEFAZIZ.ATAK_Amenity a, TAOSEEFAZIZ.ATAK_AmenityRating rating
WHERE a.amenityNo = rating.amenityNo
AND rating.DATERATED >= TO_DATE('&START_DATE', 'YYYY-MM-DD')
AND rating.DATERATED <= TO_DATE('&END_DATE', 'YYYY-MM-DD')
GROUP BY a.name
ORDER BY AVG(rating.score) DESC;






