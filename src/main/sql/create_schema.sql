
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(15) NOT NULL UNIQUE CHECK (LENGTH(username) >= 2 AND LENGTH(username) <= 15),
    email VARCHAR(100) NOT NULL UNIQUE CHECK (email LIKE '%@%'),
    password VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Tokens (
    token VARCHAR(100) PRIMARY KEY,
    user_id INT NOT NULL,
    last_used_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Genres (
    genre_id SERIAL PRIMARY KEY,
    genre_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Movies (
    movie_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    release_date DATE,
    duration INT,
    rating INT CHECK (rating >= 0 AND rating <= 10)
);

CREATE TABLE MovieGenres (
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id),
    FOREIGN KEY (genre_id) REFERENCES Genres(genre_id)
);

CREATE TABLE Shows (
    show_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    rating INT CHECK (rating >= 0 AND rating <= 10)
);

CREATE TABLE Seasons (
    season_id SERIAL PRIMARY KEY,
    show_id INT NOT NULL,
    season_number INT NOT NULL,
    rating INT CHECK (rating >= 0 AND rating <= 10),
    FOREIGN KEY (show_id) REFERENCES Shows(show_id)
);

CREATE TABLE Episodes (
    episode_id SERIAL PRIMARY KEY,
    season_id INT NOT NULL,
    episode_number INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    release_date DATE,
    duration INT,
    rating INT CHECK (rating >= 0 AND rating <= 10),
    FOREIGN KEY (season_id) REFERENCES Seasons(season_id)
);

CREATE TABLE ShowGenres (
    show_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (show_id, genre_id),
    FOREIGN KEY (show_id) REFERENCES Shows(show_id),
    FOREIGN KEY (genre_id) REFERENCES Genres(genre_id)
);

CREATE TABLE Games (
    game_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    release_date DATE,
    rating INT CHECK (rating >= 0 AND rating <= 10)
);

CREATE TABLE GameGenres (
    game_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id),
    FOREIGN KEY (genre_id) REFERENCES Genres(genre_id)
);

CREATE TABLE MovieReviews (
    user_id INT NOT NULL,
    movie_id INT NOT NULL,
    external_rating INT NOT NULL CHECK (external_rating >= 0 AND external_rating <= 100),
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 10),
    review TEXT,
    PRIMARY KEY (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

CREATE TABLE ShowReviews (
    user_id INT NOT NULL,
    show_id INT NOT NULL,
    external_rating INT NOT NULL CHECK (external_rating >= 0 AND external_rating <= 100),
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 10),
    review TEXT,
    PRIMARY KEY (user_id, show_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (show_id) REFERENCES Shows(show_id)
);

CREATE TABLE EpisodeReviews (
    user_id INT NOT NULL,
    episode_id INT NOT NULL,
    external_rating INT NOT NULL CHECK (external_rating >= 0 AND external_rating <= 100),
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 10),
    review TEXT,
    PRIMARY KEY (user_id, episode_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (episode_id) REFERENCES Episodes(episode_id)
);

CREATE TABLE GameReviews (
    user_id INT NOT NULL,
    game_id INT NOT NULL,
    external_rating INT NOT NULL CHECK (external_rating >= 0 AND external_rating <= 100),
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 10),
    review TEXT,
    PRIMARY KEY (user_id, game_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id)
);