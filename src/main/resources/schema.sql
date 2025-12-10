CREATE TABLE heroes (
                        hero_id INT PRIMARY KEY AUTO_INCREMENT,
                        first_name VARCHAR(50),
                        last_name VARCHAR(50),
                        alias VARCHAR(50) UNIQUE ,
                        origin VARCHAR(30),
                        year_created INT,
                        universe VARCHAR(50)  -- e.g., "Marvel", "DC"
);
