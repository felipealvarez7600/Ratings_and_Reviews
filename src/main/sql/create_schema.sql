
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
    overview VARCHAR(1000) NOT NULL,
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

CREATE TABLE IF NOT EXISTS TVShow (
    adult BOOLEAN,
    backdrop_path VARCHAR(255),
    episode_run_time INT[],
    first_air_date VARCHAR(255),
    homepage VARCHAR(255),
    id INT PRIMARY KEY,
    in_production BOOLEAN,
    languages VARCHAR(255)[],
    last_air_date VARCHAR(255),
    last_episode_to_air Int,
    name VARCHAR(255) NOT NULL,
    next_episode_to_air INT,
    number_of_episodes INT,
    number_of_seasons INT,
    origin_country VARCHAR(255)[],
    original_language VARCHAR(255),
    original_name VARCHAR(255),
    overview VARCHAR(1000) NOT NULL,
    popularity FLOAT,
    poster_path VARCHAR(255),
    status VARCHAR(255),
    tagline VARCHAR(255),
    type VARCHAR(255),
    vote_average DOUBLE PRECISION,
    vote_count INT
);

CREATE TABLE IF NOT EXISTS TVShow_Genres (
    show_id INT,
    genre_id INT,
    PRIMARY KEY (show_id, genre_id),
    FOREIGN KEY (show_id) REFERENCES TVShow(id),
    FOREIGN KEY (genre_id) REFERENCES Genres(id)
);

CREATE TABLE IF NOT EXISTS TVShow_Production_Companies (
    show_id INT,
    production_company_id INT,
    PRIMARY KEY (show_id, production_company_id),
    FOREIGN KEY (show_id) REFERENCES TVShow(id),
    FOREIGN KEY (production_company_id) REFERENCES Production_Companies(id)
);

CREATE TABLE IF NOT EXISTS TVShow_Production_Country (
    show_id INT,
    production_country_id VARCHAR(255),
    PRIMARY KEY (show_id, production_country_id),
    FOREIGN KEY (show_id) REFERENCES TVShow(id),
    FOREIGN KEY (production_country_id) REFERENCES Production_Countries(iso31661)
);

CREATE TABLE IF NOT EXISTS TVShow_Spoken_Languages (
    show_id INT,
    spoken_language_id VARCHAR(255),
    PRIMARY KEY (show_id, spoken_language_id),
    FOREIGN KEY (show_id) REFERENCES TVShow(id),
    FOREIGN KEY (spoken_language_id) REFERENCES Spoken_Languages(iso6391)
);

CREATE TABLE IF NOT EXISTS Networks (
    logo_path VARCHAR(255),
    name VARCHAR(255),
    id INT PRIMARY KEY,
    origin_country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS TVShow_Networks (
    show_id INT,
    network_id INT,
    PRIMARY KEY (show_id, network_id),
    FOREIGN KEY (show_id) REFERENCES TVShow(id),
    FOREIGN KEY (network_id) REFERENCES Networks(id)
);

CREATE TABLE IF NOT EXISTS Created_By (
    id INT PRIMARY KEY,
    credit_id VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS TVShow_Created_By (
    show_id INT,
    created_by_id INT,
    PRIMARY KEY (show_id, created_by_id),
    FOREIGN KEY (show_id) REFERENCES TVShow(id),
    FOREIGN KEY (created_by_id) REFERENCES Created_By(id)
);

CREATE TABLE IF NOT EXISTS Episode (
    air_date VARCHAR(255),
    episode_number INT,
    id INT PRIMARY KEY,
    name VARCHAR(255),
    overview VARCHAR(1000),
    production_code VARCHAR(255),
    season_number INT,
    show_id INT,
    still_path VARCHAR(255),
    vote_average DOUBLE PRECISION,
    vote_count INT,
    runtime INT,
    episode_type VARCHAR(255),
    FOREIGN KEY (show_id) REFERENCES TVShow(id)
);