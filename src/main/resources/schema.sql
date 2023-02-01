DROP TABLE IF EXISTS `Burden`;

CREATE TABLE IF NOT EXISTS `Burden` (
    `id`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
     `name` VARCHAR(50) NOT NULL,
     `quantity_male`        VARCHAR(50)  NOT NULL,
      `quantity_female`        VARCHAR(50)  NOT NULL
);