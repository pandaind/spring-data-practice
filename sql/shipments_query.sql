-- 7.1 Who receieved a 1.5kg package?
    -- The result is "Al Gore's Head".
SELECT Recipient FROM Package WHERE Weight = 1.5;

SELECT client.name FROM client JOIN Package ON client.AccountNumber = Package.Recipient WHERE package.Weight = 1.5;


SELECT Client.Name
  FROM Client JOIN Package   
  ON Client.AccountNumber = Package.Recipient 
  WHERE Package.weight = 1.5;
-- 7.2 What is the total weight of all the packages that he sent?
SELECT sum(weight) FROM Package WHERE Sender = (SELECT Recipient FROM Package WHERE Weight = 1.5);

SELECT SUM(p.weight) 
FROM Client AS c 
  JOIN Package as P 
  ON c.AccountNumber = p.Sender
  WHERE c.Name = "Al Gore's Head";
