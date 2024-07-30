
CREATE TABLE IF NOT EXISTS Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(15) NOT NULL UNIQUE CHECK (LENGTH(username) >= 2 AND LENGTH(username) <= 15),
    email VARCHAR(100) NOT NULL UNIQUE CHECK (email LIKE '%@%'),
    password VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Tokens (
    token VARCHAR(100) PRIMARY KEY,
    user_id INT NOT NULL,
    last_used_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE IF NOT EXISTS Movies (
    original_language VARCHAR(10),
    imdb_id VARCHAR(255) NOT NULL,
    video BOOLEAN,
    title VARCHAR(255) NOT NULL,
    backdrop_path VARCHAR(255),
    revenue BIGINT,
    popularity FLOAT,
    id INT PRIMARY KEY,
    vote_count INT,
    budget BIGINT,
    overview VARCHAR(255) NOT NULL,
    original_title VARCHAR(255),
    runtime INT,
    poster_path VARCHAR(255),
    release_date VARCHAR(255),
    vote_average DOUBLE PRECISION,
    belongs_to_collection VARCHAR(255),
    tagline VARCHAR(255),
    adult BOOLEAN,
    homepage VARCHAR(255),
    status VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Genres (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Production_Companies(
    logo_path VARCHAR(255),
    name VARCHAR(255),
    id INT PRIMARY KEY,
    origin_country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Spoken_Languages(
    english_name VARCHAR(255),
    iso6391 VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Production_Countries(
    iso31661 VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Movie_Genres (
    movie_id INT,
    genre_id INT,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(id),
    FOREIGN KEY (genre_id) REFERENCES Genres(id)
);

CREATE TABLE IF NOT EXISTS Movie_Production_Companies (
    movie_id INT,
    production_company_id INT,
    PRIMARY KEY (movie_id, production_company_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(id),
    FOREIGN KEY (production_company_id) REFERENCES Production_Companies(id)
);

CREATE TABLE IF NOT EXISTS Movie_Spoken_Languages (
    movie_id INT,
    spoken_language_id VARCHAR(255),
    PRIMARY KEY (movie_id, spoken_language_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(id),
    FOREIGN KEY (spoken_language_id) REFERENCES Spoken_Languages(iso6391)
);

CREATE TABLE IF NOT EXISTS Movie_Production_Countries (
    movie_id INT,
    production_country_id VARCHAR(255),
    PRIMARY KEY (movie_id, production_country_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(id),
    FOREIGN KEY (production_country_id) REFERENCES Production_Countries(iso31661)
);
