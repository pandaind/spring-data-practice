USE sql_practice;
-- 3.1 Select all warehouses.
SELECT * FROM Warehouses;

-- 3.2 Select all boxes with a value larger than $150.
SELECT * FROM Boxes WHERE Value > 150;

-- 3.3 Select all distinct contents in all the boxes.
SELECT DISTINCT Contents FROM Boxes;

-- 3.4 Select the average value of all the boxes.
SELECT AVG(Value) FROM Boxes;

-- 3.5 Select the warehouse code and the average value of the boxes in each warehouse.
SELECT AVG(B.Value), B.Warehouse FROM Boxes B GROUP BY B.Warehouse;

-- 3.6 Same as previous exercise, but select only those warehouses where the average value
-- of the boxes is greater than 150.
SELECT AVG(B.Value) AVG, B.Warehouse FROM Boxes B GROUP BY B.Warehouse HAVING AVG > 150;

-- 3.7 Select the code of each box, along with the name of the city the box is located in.
SELECT B.Code, W.Location FROM Boxes B, Warehouses W WHERE B.Warehouse = W.Code;

-- 3.8 Select the warehouse codes, along with the number of boxes in each warehouse.
-- Optionally, take into account that some warehouses are empty
-- (i.e., the box count should show up as zero, instead of omitting the warehouse from the result).
SELECT COUNT(*), B.Warehouse FROM Boxes B GROUP BY B.Warehouse;

-- 3.9 Select the codes of all warehouses that are saturated
-- (a warehouse is saturated if the number of boxes in it is larger than the warehouse's capacity).
SELECT inr.Code, inr.Capacity FROM
            (SELECT COUNT(*) no_of_box, W.Code, W.Capacity FROM Boxes B, Warehouses W
             WHERE B.Warehouse = W.Code GROUP BY W.Code) as inr
WHERE inr.no_of_box > inr.Capacity;

-- 3.10 Select the codes of all the boxes located in Chicago.
SELECT B.Code, W.Location FROM Boxes B, Warehouses W WHERE B.Warehouse = W.Code AND W.Location = 'Chicago';

-- 3.11 Create a new warehouse in New York with a capacity for 3 boxes.
INSERT INTO Warehouses VALUES (6, 'New York', 3);

-- 3.12 Create a new box, with code "H5RT",
-- containing "Papers" with a value of $200, and located in warehouse 2.
INSERT INTO Boxes VALUES('H5RT', 'Papers', 200, 2);

-- 3.13 Reduce the value of all boxes by 15%.
UPDATE Boxes SET Value = Value * 0.85;

-- 3.14 Remove all boxes with a value lower than $100.
DELETE FROM Boxes WHERE Value < 100;

-- 3.15 Remove all boxes from saturated warehouses.
DELETE FROM Boxes WHERE Warehouse IN (
    SELECT inr.Code as Code FROM
        (SELECT COUNT(*) no_of_box, W.Code, W.Capacity FROM Boxes B, Warehouses W
         WHERE B.Warehouse = W.Code GROUP BY W.Code) as inr
    WHERE inr.no_of_box > inr.Capacity
    );

-- 3.16 Add Index for column "Warehouse" in table "boxes"
-- !!!NOTE!!!: index should NOT be used on small tables in practice
-- CREATE INDEX INDEX_WAREHOUSE ON Boxes (Warehouse);

-- 3.17 Print all the existing indexes
-- !!!NOTE!!!: index should NOT be used on small tables in practice
-- SHOW INDEX FROM Boxes FROM sql_practice;
-- SHOW INDEX FROM sql_practice.Boxes;

-- 3.18 Remove (drop) the index you added just
-- !!!NOTE!!!: index should NOT be used on small tables in practice