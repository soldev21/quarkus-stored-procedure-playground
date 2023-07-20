INSERT INTO bingo."tournaments" (status)
SELECT 0
FROM generate_series(1, 1000000);