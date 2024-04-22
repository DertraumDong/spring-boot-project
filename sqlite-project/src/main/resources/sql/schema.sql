CREATE TABLE IF NOT EXISTS interview_question (
                                                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                                                  question_text TEXT NOT NULL,
                                                  correct_answer TEXT NOT NULL,
                                                  explanation TEXT,
                                                  difficulty INTEGER DEFAULT 1 -- 题目难度，可以是 1 到 5 的整数值
);

CREATE TABLE IF NOT EXISTS interview_record (
                                                id INTEGER PRIMARY KEY,
                                                user_id INTEGER NOT NULL,
                                                start_time TEXT NOT NULL,
                                                end_time TEXT
);

CREATE TABLE IF NOT EXISTS interview_question_record (
                                                         id INTEGER PRIMARY KEY,
                                                         interview_record_id INTEGER NOT NULL,
                                                         question_id INTEGER NOT NULL,
                                                         user_answer TEXT
);