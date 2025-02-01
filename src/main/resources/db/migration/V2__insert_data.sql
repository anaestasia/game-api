-- db/migration/V1__create_tables.sql
USE db_game_api;

-- Insertion de données dans la table characters
INSERT INTO characters (name, pv, atq, def, speed) VALUES
('Narvath', 10, 2, 0, 1),
('Imadi', 10, 2, 0, 1);

-- Insertion de données dans la table characters
INSERT INTO items (name, type, description, bonus, stat) VALUES
('Choipeau', 'HELMET', 'Un chapeau léger qui ne protège que du soleil et qui ne décide de rien.', 5, 'def'),
('Casque Ade', 'HELMET', 'Un casque solide pour protéger des objets qui subissent la gravité.', 10, 'def'),
('Tenue d\'Adam', 'ARMOR', 'Une feuille de vigne qui protège votre intimité des voyeurs tout en profitant de la brise.', 10, 'def'),
('Cotte de Maillestro', 'ARMOR', 'Une cote de maille pour avoir la classe des chevaliers et marcher au rythme du gling-gling.', 20, 'def'),
('Bâtronc', 'ONE_HAND_WEAPON', 'Un bâton pour ceux qui veulent se la jouer Gandalf. Alors, ça passe ou pas ?', 5, 'atq'),
('Coutopointu', 'ONE_HAND_WEAPON', 'Une lame courte parce qu\'on a toujours besoin de pouvoir découper un bon steack ou un ennemi. Ou même tailler des steacks dans les ennemis.', 10, 'atq'),
('Pan Taymore', 'ONE_HAND_WEAPON', 'Un pistolet pour ceux qui préfèrent les menaces simples et efficaces.', 15, 'atq'),
('Péri Gourdin', 'TWO_HAND_WEAPON', 'Un gourdin qui peut taper de manière périphérique, ou écraser vos pommes de terre.', 10, 'atq'),
('Lance Inante', 'TWO_HAND_WEAPON', 'Une lance qui peut empaler des ennemis et provoque des douleurs intense.', 15, 'atq'),
('Lance Iraptor', 'TWO_HAND_WEAPON', 'Une lance préhistorique acérée pour les fans du jurassik.', 20, 'atq'),
('Ecu Villon', 'SHIELD', 'Un bouclier plutôt fin qui protège qu\'une seule partie du corps à la fois.', 5, 'def'),
('Ecu Reuil', 'SHIELD', 'Un bouclier de fourrure pour amortir l\'atterrisage des projectiles.', 10, 'def'),
('Ano Stekké', 'RING', 'Un anneau qui vous rend plus confiant, vous affrontez courageusement le danger avec un bonus de PV.', 5, 'pv'),
('Ano Rexique', 'RING', 'Un anneau qui vous rend plus léger et vous confère un bonus de vitesse.', 2, 'speed'),
('Bague Are', 'RING', 'Un anneau qui vous aidera à développer vos compétences de combat de rue et vous donne un bonus d\'attaque.', 10, 'atq'),
('Bague Era', 'RING', 'Un anneau qui vous rend aussi agile qu\'un félin et vous permet d\'esquiver plus facilement et vous donne un bonus de défense.', 10, 'def'),
('Chausse Tentatwar', 'SHOES', 'Des chaussures qui claquent pour rendre jaloux vos ennemis mais ne permet pas de courir.', 5, 'def'),
('Botte Eurfly', 'SHOES', 'Des chaussures légères avec une semelle en coton qui vous donnent l\'impression de voler.', 5, 'speed');
