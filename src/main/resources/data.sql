INSERT INTO META_STATUS (id, code, title) VALUES (1, 'STAT001', 'Enabled');
INSERT INTO META_STATUS (id, code, title) VALUES (2, 'STAT002', 'Disabled');

INSERT INTO META_CURRENCY (id, title, symbol, code) VALUES (1, 'Canadian Dollar', '$', 'CAD');
INSERT INTO META_CURRENCY (id, title, symbol, code) VALUES (2, 'Pakistani Rupee', 'Rs', 'PKR');
INSERT INTO META_CURRENCY (id, title, symbol, code) VALUES (3, 'Korean Won', '₩', 'KRW');
INSERT INTO META_CURRENCY (id, title, symbol, code) VALUES (4, 'US Dollar', '$', 'USD');

INSERT INTO STORE (id, title, address, phone, file_name, file_path, file_url, fk_status, fk_currency) VALUES (1, 'Tim Hortons', 'London', '-', '-', '-', '-', '1', '1');

INSERT INTO USER (id, username, password, fk_store, fk_status) VALUES (1, 'admin', 'admin', '1', '1');

INSERT INTO ROLE (id, role, fk_user) VALUES (1, 'ADMIN', '1');
INSERT INTO ROLE (id, role, fk_user) VALUES (2, 'USER', '1');

INSERT INTO SIZE (title, fk_store, fk_status) VALUES ('combo', 1, 1);
INSERT INTO SIZE (title, fk_store, fk_status) VALUES ('each', 1, 1);
INSERT INTO SIZE (title, fk_store, fk_status) VALUES ('3 pcs', 1, 1);
INSERT INTO SIZE (title, fk_store, fk_status) VALUES ('5 pcs', 1, 1);
INSERT INTO SIZE (title, fk_store, fk_status) VALUES ('10 pcs', 1, 1);

INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('turkey club', '1', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');
INSERT INTO MENU (title, sort_order, start_date, end_date, ORIGINAL_FILE_NAME, file_name, file_path, file_url, fk_store, fk_status) VALUES ('chicken club', '2', '2018-09-01', '2018-10-31', 'lion.jpg', '4.png', '/uploads/', '-', '1', '1');

INSERT INTO MENU_SIZE (ID, PRICE, FK_MENU, FK_SIZE, FK_STATUS) VALUES (1, '10.0', 1, 1, 1);