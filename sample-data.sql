-- Sample Data for VocaloCart Shopping Mall
-- Run this script to populate the database with test data

-- =====================================================
-- USERS (Passwords are BCrypt encoded: all passwords are "password123")
-- =====================================================

INSERT INTO users (email, password, name, phone, address, role, created_at, updated_at) VALUES
('admin@vocalocart.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Admin User', '010-0000-0000', '1 Admin Street, Seoul', 'ADMIN', NOW(), NOW()),
('alice@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Alice Johnson', '010-1234-5678', '123 Gangnam Street, Seoul', 'USER', NOW(), NOW()),
('bob@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Bob Smith', '010-2345-6789', '456 Hongdae Avenue, Seoul', 'USER', NOW(), NOW()),
('carol@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Carol White', '010-3456-7890', '789 Myeongdong Road, Seoul', 'USER', NOW(), NOW()),
('david@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'David Lee', '010-4567-8901', '321 Itaewon Street, Seoul', 'USER', NOW(), NOW());

-- =====================================================
-- ADDRESSES
-- =====================================================

INSERT INTO addresses (user_id, recipient_name, phone_number, address_line1, address_line2, city, state, postal_code, country, is_default, created_at, updated_at) VALUES
(2, 'Alice Johnson', '010-1234-5678', '123 Gangnam-daero', 'Apt 101', 'Seoul', 'Gangnam-gu', '06000', 'South Korea', true, NOW(), NOW()),
(2, 'Alice Johnson', '010-1234-5678', '456 Teheran-ro', 'Office Building 5F', 'Seoul', 'Gangnam-gu', '06100', 'South Korea', false, NOW(), NOW()),
(3, 'Bob Smith', '010-2345-6789', '789 Hongdae-gil', 'Studio 202', 'Seoul', 'Mapo-gu', '04000', 'South Korea', true, NOW(), NOW()),
(4, 'Carol White', '010-3456-7890', '321 Myeongdong-gil', 'Villa 301', 'Seoul', 'Jung-gu', '04500', 'South Korea', true, NOW(), NOW());

-- =====================================================
-- CATEGORIES
-- =====================================================

INSERT INTO categories (name, description, created_at, updated_at) VALUES
('Albums', 'Vocaloid music albums and singles', NOW(), NOW()),
('Figures', 'Character figures and collectibles', NOW(), NOW()),
('Plushies', 'Soft toys and plush merchandise', NOW(), NOW()),
('Clothing', 'T-shirts, hoodies, and apparel', NOW(), NOW()),
('Accessories', 'Keychains, pins, and small items', NOW(), NOW()),
('Posters', 'Art prints and wall posters', NOW(), NOW());

-- =====================================================
-- PRODUCTS
-- =====================================================

-- Albums
INSERT INTO products (name, description, price, stock_quantity, category_id, image_url, created_at, updated_at) VALUES
('Hatsune Miku - Project DIVA Mega Mix Album', 'Complete soundtrack featuring 100+ songs from Project DIVA series', 29.99, 50, 1, '/images/products/album-diva.jpg', NOW(), NOW()),
('Kagamine Rin & Len - Best Collection', 'Greatest hits compilation of Rin & Len duets', 24.99, 40, 1, '/images/products/album-rinlen.jpg', NOW(), NOW()),
('KAITO - Blue Memories', 'Emotional ballad collection', 22.99, 35, 1, '/images/products/album-kaito.jpg', NOW(), NOW()),
('Megurine Luka - Just Be Friends Special Edition', 'Limited edition with bonus tracks', 34.99, 20, 1, '/images/products/album-luka.jpg', NOW(), NOW());

-- Figures
INSERT INTO products (name, description, price, stock_quantity, category_id, image_url, created_at, updated_at) VALUES
('Hatsune Miku Racing Queen Ver. 1/8 Scale Figure', 'Highly detailed PVC figure with stand', 89.99, 15, 2, '/images/products/figure-miku-racing.jpg', NOW(), NOW()),
('Kagamine Rin Append Ver. Figma', 'Articulated action figure with accessories', 54.99, 25, 2, '/images/products/figure-rin-append.jpg', NOW(), NOW()),
('Megurine Luka Nendoroid', 'Cute chibi-style figure with multiple expressions', 44.99, 30, 2, '/images/products/figure-luka-nendoroid.jpg', NOW(), NOW()),
('MEIKO & KAITO Twin Pack Figure', 'Special set featuring both characters', 129.99, 10, 2, '/images/products/figure-meiko-kaito.jpg', NOW(), NOW()),
('Hatsune Miku Symphony 2021 Ver. Premium Figure', 'Concert outfit premium quality figure', 149.99, 8, 2, '/images/products/figure-miku-symphony.jpg', NOW(), NOW());

-- Plushies
INSERT INTO products (name, description, price, stock_quantity, category_id, image_url, created_at, updated_at) VALUES
('Hatsune Miku Jumbo Plush 16"', 'Super soft and huggable Miku plushie', 39.99, 45, 3, '/images/products/plush-miku-jumbo.jpg', NOW(), NOW()),
('Kagamine Len Chibi Plush 8"', 'Adorable mini Len plush with banana accessory', 19.99, 60, 3, '/images/products/plush-len-chibi.jpg', NOW(), NOW()),
('Snow Miku 2023 Plush', 'Limited edition Snow Festival design', 34.99, 25, 3, '/images/products/plush-snow-miku.jpg', NOW(), NOW()),
('Sakura Miku Plush 12"', 'Cherry blossom themed spring edition', 29.99, 35, 3, '/images/products/plush-sakura-miku.jpg', NOW(), NOW());

-- Clothing
INSERT INTO products (name, description, price, stock_quantity, category_id, image_url, created_at, updated_at) VALUES
('Hatsune Miku Classic Logo T-Shirt', '100% cotton, available in multiple sizes', 24.99, 100, 4, '/images/products/tshirt-miku-logo.jpg', NOW(), NOW()),
('Magical Mirai 2023 Hoodie', 'Official concert merchandise hoodie', 49.99, 40, 4, '/images/products/hoodie-magical-mirai.jpg', NOW(), NOW()),
('Vocaloid All-Stars Baseball Cap', 'Adjustable cap with embroidered logo', 19.99, 70, 4, '/images/products/cap-vocaloid.jpg', NOW(), NOW()),
('Project DIVA Track Jacket', 'Sporty zip-up jacket with game graphics', 59.99, 30, 4, '/images/products/jacket-project-diva.jpg', NOW(), NOW());

-- Accessories
INSERT INTO products (name, description, price, stock_quantity, category_id, image_url, created_at, updated_at) VALUES
('Hatsune Miku Acrylic Keychain Set', 'Set of 5 keychains with different expressions', 14.99, 80, 5, '/images/products/keychain-miku-set.jpg', NOW(), NOW()),
('Vocaloid Character Pin Badge Collection', '12-piece enamel pin set', 29.99, 50, 5, '/images/products/pins-vocaloid-collection.jpg', NOW(), NOW()),
('Leek Pen (Miku Official Merchandise)', 'Novelty ballpoint pen shaped like a leek', 9.99, 120, 5, '/images/products/pen-leek.jpg', NOW(), NOW()),
('Hatsune Miku Phone Ring Holder', 'Universal phone grip with rotating stand', 12.99, 90, 5, '/images/products/phone-ring-miku.jpg', NOW(), NOW()),
('Kagamine Twins Enamel Mug', 'Ceramic mug with metallic finish', 16.99, 65, 5, '/images/products/mug-kagamine.jpg', NOW(), NOW());

-- Posters
INSERT INTO products (name, description, price, stock_quantity, category_id, image_url, created_at, updated_at) VALUES
('Hatsune Miku 16th Anniversary Poster', 'High-quality art print 24x36 inches', 19.99, 55, 6, '/images/products/poster-miku-16th.jpg', NOW(), NOW()),
('Project DIVA Arcade Art Poster Set', 'Set of 3 posters from arcade game', 34.99, 30, 6, '/images/products/poster-diva-set.jpg', NOW(), NOW()),
('Magical Mirai Concert Poster 2023', 'Official concert promotional poster', 24.99, 40, 6, '/images/products/poster-magical-mirai.jpg', NOW(), NOW()),
('Vocaloid Family Group Art Print', 'All main Vocaloids illustrated poster', 22.99, 45, 6, '/images/products/poster-vocaloid-family.jpg', NOW(), NOW());

-- =====================================================
-- SHOPPING CARTS (Some users already have items in cart)
-- =====================================================

-- Alice's cart
INSERT INTO carts (user_id, created_at, updated_at) VALUES
(1, NOW(), NOW());

INSERT INTO cart_items (cart_id, product_id, quantity, created_at, updated_at) VALUES
(1, 1, 1, NOW(), NOW()),  -- Miku Album
(1, 6, 1, NOW(), NOW()),  -- Miku Racing Figure
(1, 10, 2, NOW(), NOW()); -- Miku Plush

-- Bob's cart
INSERT INTO carts (user_id, created_at, updated_at) VALUES
(2, NOW(), NOW());

INSERT INTO cart_items (cart_id, product_id, quantity, created_at, updated_at) VALUES
(2, 15, 1, NOW(), NOW()), -- T-Shirt
(2, 19, 1, NOW(), NOW()); -- Keychain Set

-- =====================================================
-- SAMPLE ORDERS (Previous purchase history)
-- =====================================================

-- Alice's completed order
INSERT INTO orders (user_id, total_amount, order_status, payment_method, shipping_address_id, created_at, updated_at) VALUES
(1, 74.97, 'DELIVERED', 'CREDIT_CARD', 1, DATE_SUB(NOW(), INTERVAL 7 DAY), DATE_SUB(NOW(), INTERVAL 2 DAY));

INSERT INTO order_items (order_id, product_id, quantity, price, created_at, updated_at) VALUES
(1, 7, 1, 54.99, DATE_SUB(NOW(), INTERVAL 7 DAY), DATE_SUB(NOW(), INTERVAL 7 DAY)),
(1, 11, 1, 19.99, DATE_SUB(NOW(), INTERVAL 7 DAY), DATE_SUB(NOW(), INTERVAL 7 DAY));

-- Carol's completed order
INSERT INTO orders (user_id, total_amount, order_status, payment_method, shipping_address_id, created_at, updated_at) VALUES
(3, 129.97, 'SHIPPED', 'PAYPAL', 4, DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY));

INSERT INTO order_items (order_id, product_id, quantity, price, created_at, updated_at) VALUES
(2, 5, 1, 89.99, DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_SUB(NOW(), INTERVAL 3 DAY)),
(2, 16, 1, 49.99, DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_SUB(NOW(), INTERVAL 3 DAY));

-- Bob's pending order
INSERT INTO orders (user_id, total_amount, order_status, payment_method, shipping_address_id, created_at, updated_at) VALUES
(2, 44.98, 'PROCESSING', 'CREDIT_CARD', 3, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY));

INSERT INTO order_items (order_id, product_id, quantity, price, created_at, updated_at) VALUES
(3, 3, 1, 22.99, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY)),
(3, 20, 1, 29.99, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY));

-- =====================================================
-- Query to verify inserted data
-- =====================================================
-- SELECT COUNT(*) as user_count FROM users;
-- SELECT COUNT(*) as product_count FROM products;
-- SELECT COUNT(*) as order_count FROM orders;
-- SELECT c.name as category, COUNT(p.id) as product_count 
-- FROM categories c 
-- LEFT JOIN products p ON c.id = p.category_id 
-- GROUP BY c.id, c.name;
