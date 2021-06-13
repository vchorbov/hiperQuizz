SELECT u.`username` as "Author", q.`title` as "Quiz title" , q.`description`, q.`expected_duration` AS "Expected duration", q.`tags`
FROM `quizzes` as q 
LEFT JOIN `users`as u
ON q.`author_id` = u.`id`
ORDER BY q.`author_id` ASC;

SELECT quiz.`title`, question.`text`, ans.`text`, ans.`score`
FROM `quizzes` AS quiz
LEFT JOIN `questions` AS question
ON  question.`quiz_id` = quiz.`id`
LEFT JOIN `answers` AS ans
ON question.`answer_id` = ans.`id`
ORDER BY ans.`score` DESC