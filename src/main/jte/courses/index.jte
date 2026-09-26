@import hexlet.code.dto.CoursesPage
@import hexlet.code.model.Course

@param CoursesPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${page.getHeader()}</title>
</head>
<body>
    <h1>${page.getHeader()}</h1>

    <ul>
    @for(Course course : page.getCourses())
        <li>
            <a href="/courses/${course.getId()}">${course.getName()}</a>
            — ${course.getDescription()}
        </li>
    @endfor
    </ul>
</body>
</html>
