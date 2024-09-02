DROP TABLE if EXISTS groups;
-- リンクを管理する
CREATE TABLE groups (
    id text PRIMARY KEY,
    group_name text NOT NULL,
    -- all_money INTEGER DEFAULT 0

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP --更新日時
);

DROP TABLE if EXISTS users;
-- ユーザを管理する
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    group_id text REFERENCES groups(id),
    user_name text NOT NULL

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP --更新日時
);

DROP TABLE if EXISTS events;
-- イベントを管理する
CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    group_id text REFERENCES groups(id),
    event_name text NOT NULL,
    total_amount INTEGER NOT NULL, --イベント全体の合計金額
    paid_user_id INTEGER REFERENCES users(id),-- 支払ったユーザID
    
    event_date DATE,
    event_time TIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP --更新日時
);

DROP TABLE if EXISTS event_instead_users;
-- 誰が誰の分を立て替えたか
CREATE TABLE event_instead_users (
    id SERIAL PRIMARY KEY,
    group_id text REFERENCES groups(id),
    event_id INTEGER REFERENCES events(id),
    user_id INTEGER REFERENCES users(id),
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP --更新日時
);

DROP TABLE if EXISTS debts;
-- ユーザの借金を管理する
CREATE TABLE debts (
    id SERIAL PRIMARY KEY,
    group_id text REFERENCES groups(id),
    creditor_id INTEGER REFERENCES users(id),  -- 立て替えた人のID
    debtor_id INTEGER REFERENCES users(id),    -- 借りた人のID
    amount INTEGER NOT NULL,            -- 借金の金額
    event_id INTEGER REFERENCES events(id),    -- どのイベントに関連するか
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 1イベントの作成:
-- 新しいイベントが作成され、events テーブルに保存されます。
-- 支払った人のIDが paid_user_id に保存され、イベントの合計金額が total_amount に保存されます。

-- 2立替情報の管理:
-- イベントに参加した各ユーザの立て替え情報が event_instead_users テーブルに保存されます。
-- 立て替えられた各ユーザに対して、share_amount の金額が保存されます。

-- 3負債の計算:
-- debts テーブルに、各ユーザ間の負債関係が保存されます。
-- 支払ったユーザ（creditor_id）と立て替えられたユーザ（debtor_id）の情報が保存され、金額が amount に記録されます。