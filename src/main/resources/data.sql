-- Inserting Data into DeliveryNote
INSERT INTO DeliveryNote (id, price, starting_date, finishing_date)
VALUES (1, 100.50, '2024-01-15', '2024-01-20'),
       (2, 250.75, '2024-02-01', '2024-02-05'),
       (3, 320.80, '2024-03-10', '2024-03-15'),
       (4, 150.25, '2024-04-05', '2024-04-10'),
       (5, 410.00, '2024-05-20', '2024-05-25');

-- Inserting Data into Fish
INSERT INTO Fish (id_fish, name, scientific_name, habitat, sex, number_of_individuals, description)
VALUES (1, 'Salmon', 'Salmo salar', 'Freshwater', 'M', 500, 'Commonly found in rivers and oceans.'),
       (2, 'Clownfish', 'Amphiprioninae', 'Coral reefs', 'F', 50, 'Known for its bright colors and symbiotic relationship with sea anemones.'),
       (3, 'Tuna', 'Thunnus thynnus', 'Open ocean', 'M', 300, 'Fast swimming, migratory species.'),
       (4, 'Goldfish', 'Carassius auratus', 'Ponds and freshwater', 'F', 150, 'Popular aquarium fish with bright orange color.'),
       (5, 'Betta', 'Betta splendens', 'Rice paddies and shallow waters', 'M', 75, 'Aggressive species, also known as Siamese fighting fish.'),
       (6, 'Angelfish', 'Pterophyllum scalare', 'Freshwater rivers', 'F', 120, 'Graceful fish with distinctive triangular fins.');

-- Inserting Data into Product
INSERT INTO Product (id_product, name, price, category, type)
VALUES (1, 'Fish Food', 15.00, 'Food', 'Pellets'),
       (2, 'Aquarium Filter', 75.50, 'Equipment', 'Filter'),
       (3, 'Water Heater', 40.00, 'Equipment', 'Heater'),
       (4, 'Aquarium Light', 60.00, 'Equipment', 'LED Light'),
       (5, 'Aquarium Decoration - Coral', 20.50, 'Decoration', 'Coral'),
       (6, 'Aquarium Gravel', 10.00, 'Decoration', 'Gravel'),
       (7, 'Water Conditioner', 12.00, 'Chemicals', 'Conditioner');

-- Inserting Data into Tank
INSERT INTO Tank (id_tank, size, category)
VALUES (1, 250.00, 'Saltwater'),
       (2, 500.00, 'Freshwater'),
       (3, 300.00, 'Freshwater'),
       (4, 150.00, 'Saltwater'),
       (5, 750.00, 'Saltwater'),
       (6, 200.00, 'Freshwater');
