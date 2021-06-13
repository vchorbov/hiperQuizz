INSERT INTO `users` (`username`, `email`, `password`, `gender`, `description`, `metadata`) VALUES
("zina", "zina@mail.com", "123asd", 0, "I am zina and am currently taking part in the Java Accademy.", "student, java, academy");

INSERT INTO `users` (`username`, `email`, `password`, `gender`, `description`, `metadata`) VALUES
("zoro", "zoro@fake-mail.com", "qweqwe", 1, "Hey, here is zoro, the master of the quizzes and java student.", "student, java, master"),
("zeus", "zeus@olimp.god", "tunder", 1, "I am the god of the quizzes.", "quizzes, god, olimp"),
("tanq", "tanq@gmail.com", "tan4eto", 0, "I am tanq.", "tanq, user");

INSERT INTO `quizzes` (`title`, `author_id`, `description`, `expected_duration`, `tags`, `metadata`) VALUES
("Effective Java", 3, "This quizz aims to test your understanding of the book.", 20, "java, Joshua Bloch", "technical"),
("Java Academy", 1, "Test your understanding of java after the academy.", 60, "java, OOP, streams, multithreading, IO", ""),
("SPRING", 1, "SPRING the framework ", 60, "java, spring, mvc", "programming"),
("JPA", 2, "JPA API and ORMs in general", 60, "java, sql, presistence", "db");

INSERT INTO `answers` (`text`, `score`) VALUES
("1995", 10),
("Yes", 10),
("Joshua Bloch", 20),
("Web framework", 50),
("Java Persistence API", 100),
("Object Relational Mapping", 100);

INSERT INTO `questions` (`quiz_id`, `answer_id`, `text`) VALUES 
(1, 3, "Who is the author of the book Effective Java?"),
(1, 1, "When was Java invented?" ),
(2, 2, "Is Java a C-like language?"),
(3, 4, "What is Spring?"),
(4, 5, "What does JPA stand for?"),
(4, 6, "Whata does ORM stand for?"); 
