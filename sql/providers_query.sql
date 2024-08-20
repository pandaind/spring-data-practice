USE sql_practice;

-- 1 Select the name of all the pieces. 
SELECT NAME FROM pieces;
-- 2  Select all the providers' data. 
SELECT * FROM Providers;
-- 3 Obtain the average price of each piece (show only the piece code and the average price).
SELECT piece, AVG(Price) FROM provides GROUP BY Piece;
-- 4  Obtain the names of all providers who supply piece 1.
SELECT Name FROM Providers
	WHERE Code in (
		SELECT  Provider FROM provides WHERE Piece = 1
	); 

SELECT providers.Name FROM 
				providers JOIN provides 
				ON providers.Code = provides.Provider 
				WHERE provides.Piece = 1;
-- 5 Select the name of pieces provided by a provider with code "HAL".
SELECT NAME FROM pieces WHERE CODE IN (
	SELECT piece FROM provides WHERE provider = 'HAL'
);

SELECT pieces.name FROM 
			pieces JOIN provides 
			ON pieces.Code = provides.Piece 
			WHERE provides.Provider = 'HAL';

SELECT Name FROM Pieces
  WHERE EXISTS
  (
    SELECT * FROM Provides WHERE Provider = 'HAL' AND Piece = Pieces.Code
  );
-- 6
-- For each piece, find the most expensive offering of that piece and include the piece name, provider name, and price 
-- (note that there could be two providers who supply the same piece at the most expensive price).
SELECT Pieces.Name, Providers.Name, Price
  FROM Pieces INNER JOIN Provides ON Pieces.Code = Piece
              INNER JOIN Providers ON Providers.Code = Provider
  WHERE Price =
  (
    SELECT MAX(Price) FROM Provides
    WHERE Piece = Pieces.Code
  );
-- 7 Add an entry to the database to indicate that "Skellington Supplies" (code "TNBC") will provide sprockets (code "1") for 7 cents each.
INSERT INTO Provides(Piece, Provider, Price) VALUES (1, 'TNBC', 7);
-- 8 Increase all prices by one cent.
UPDATE Provides SET Price = Price + 1;
-- 9 Update the database to reflect that "Susan Calvin Corp." (code "RBT") will not supply bolts (code 4).
DELETE FROM Provides WHERE provider = 'RBT' AND Piece = 4;
-- 10 Update the database to reflect that "Susan Calvin Corp." (code "RBT") will not supply any pieces 
-- (the provider should remain in the database).
DELETE FROM provides WHERE Provider = 'RBT';
