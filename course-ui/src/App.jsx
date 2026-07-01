import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import "./App.css";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";

function Header() {
  return (
    <header className="header">
      <Link to="/" className="logo">CourseFlow</Link>

      <nav className="nav">
        <Link to="/">Home</Link>
        <Link to="/dashboard">Courses</Link>
        <Link to="/dashboard">Dashboard</Link>
        <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">
          Docs
        </a>
      </nav>

      <Link to="/login" className="login-btn">Login</Link>
    </header>
  );
}

function Hero() {
  return (
    <section className="hero">
      <p className="tag">Spring Boot + React + Docker</p>

      <h1>
        Manage courses with a
        <br />
        premium learning dashboard.
      </h1>

      <p className="hero-text">
        A modern course management UI connected with secured Spring Boot REST APIs,
        JWT authentication, role-based access, Swagger, Docker and MySQL.
      </p>

      <div className="hero-actions">
        <Link to="/login" className="primary-btn">Get Started</Link>
        <Link to="/dashboard" className="secondary-btn">View Dashboard</Link>
      </div>
    </section>
  );
}

function FeatureCard({ title, description }) {
  return (
    <div className="feature-card">
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  );
}

function Home() {
  return (
    <>
      <Header />
      <Hero />

      <section className="features">
        <FeatureCard
          title="JWT Security"
          description="Login users securely and protect course APIs using Bearer token authentication."
        />

        <FeatureCard
          title="Role Based Access"
          description="USER can view courses. ADMIN can create, update and delete course records."
        />

        <FeatureCard
          title="Docker Ready"
          description="Run Spring Boot and MySQL together using Docker Compose."
        />
      </section>
    </>
  );
}

function App() {
  return (
    <BrowserRouter>
      <div className="app">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;