@import hexlet.code.model.Course
@import hexlet.code.model.Lesson

@param Course course
@param java.util.List<Lesson> lessons

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${course.getName()}</title>
</head>
<body>
    <a href="/courses">← Все курсы</a>
    <h1>${course.getName()}</h1>
    <p>${course.getDescription()}</p>

    <h2>Уроки</h2>
    <ol>
    @for(Lesson lesson : lessons)
        <li>
            <strong>${lesson.getName()}</strong>
            — ${lesson.getDescription()}
        </li>
    @endfor
    </ol>
</body>
</html>
