# <p align="center"><img src="asserts/logo.png" alt="WIFP Logo" width="120" /></p>
<h1 align="center">WIFP-Servlet</h1>
<p align="center">WIFP (Wisteria Inspection Feedback Platform) — a lightweight inspection reporting & feedback web app built with Java Web (Servlet/JSP)</p>
<p align="center"><strong>English</strong> | <a href="./README.md">中文</a></p>

---

## About

WIFP-Servlet (Wisteria Inspection Feedback Platform - Servlet) is a lightweight Java Web project designed for:

- Inspection reports submission (notes)
- Feedback & discussion (comments)
- Categorization and tagging

Note: This is a student project I built while taking a Java Web course at school. The early implementation is straightforward and may contain quite a few bugs. After graduation, I did some cleanup and decided to publish it for learning and sharing. Please be kind.

## Features (by module)

### 1) Inspection Notes (Note)

- Note list: shows notes in reverse chronological order
- Note details: title, content, author, publish time, view count, category, tags, and comments
- View count tracking: automatically increments on each read
- Keyword search: fuzzy search by title/content/category, etc.
- Filter by category: browse notes under a specific category
- Filter by tag: browse notes under a specific tag

### 2) Feedback Comments (Comment)

- Add comment: post a comment (title + content) to a note
- Store remote IP: the server records the comment remoteIP
- My feedback: logged-in users can view their own comment history

### 3) User System (Users)

- User registration: includes captcha validation and username availability check
- Login: supports “remember me” (via cookies)
- Logout: clears session
- Change password: available after login
- Profile management: nickname, phone number, bio, etc.
- Avatar upload/display: upload an avatar and show it in the profile card

### 4) Admin / Author Panel (Author)

Users with `isAuthor = 'Y'` are treated as admin/author.

- Create inspection note: title, content, category, tags, etc.
- Edit inspection note: update content and record update time
- Delete inspection note: remove the note and clear related comments/tags
- Category management: edit category names (propagates to related notes)
- Tag management: edit tag names (propagates in tag table)
- Author profile maintenance

## Tech Stack

### Backend

- Java 17
- Servlet 6 (`jakarta.servlet-api 6.1.0`)
- JSP + JSTL
- JDBC (handwritten SQL, no MyBatis)
- Maven (`war` packaging)

### Frontend

- Layui (UI framework)
- CKEditor 5 (rich text editor)

### Database

- MySQL (works with common 5.7/8.0 setups)

## Quick Start

### 1) Prerequisites

- JDK 17
- Tomcat 10.1+ (Servlet 6 / Jakarta namespace)
- MySQL 5.7+ / 8.0+

### 2) Initialize the database

SQL script in the project :

- `wifp.sql` (creates and uses database `wifp`, and inserts sample data)

Run it with:

- Import in Navicat / DataGrip, or
- `mysql -u  -p < wifp.sql`

### 3) Configure database connection

Edit:

- `src/main/resources/db.properties`

### 4) Build & deploy

Build the WAR (Windows):

- `mvnw.cmd -DskipTests package`

Artifact:

- `target/WIFP-Servlet.war`

Deploy `WIFP-Servlet.war` into Tomcat `webapps`, start Tomcat, then open the app in your browser.

### 5) Extra note

All users in the sample database use password `123456`.

## Project Structure

- `src/main/java/com/wisteria/controller`: Servlet controllers (routes)
- `src/main/java/com/wisteria/service`: service layer
- `src/main/java/com/wisteria/mapper`: JDBC data access layer (SQL in Java code)
- `src/main/webapp`: JSP pages and static assets (Layui / CKEditor / images, etc.)

## Screenshots

<div align="center">
  <table align="center">
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%871.png" alt="Screenshot 1" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%872.png" alt="Screenshot 2" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%873.png" alt="Screenshot 3" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%874.png" alt="Screenshot 4" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%875.png" alt="Screenshot 5" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%876.png" alt="Screenshot 6" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%877.png" alt="Screenshot 7" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%878.png" alt="Screenshot 8" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%879.png" alt="Screenshot 9" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%8710.png" alt="Screenshot 10" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%8711.png" alt="Screenshot 11" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%8712.png" alt="Screenshot 12" width="420" />
      </td>
    </tr>
  </table>
</div>

## Disclaimer

- This project is for learning and sharing. It focuses on being runnable and understandable rather than being production-grade.
- Issues and PRs are welcome.
