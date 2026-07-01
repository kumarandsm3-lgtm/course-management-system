import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [message, setMessage] = useState("");

  async function handleLogin(event) {
    event.preventDefault();

    try {
      const response = await fetch("/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: email,
          password: password,
        }),
      });

      const result = await response.json();

      if (!response.ok) {
        setMessage("Invalid email or password");
        return;
      }

      const token =
        result.token ||
        result.data?.token ||
        result.jwt ||
        result.data?.jwt;

      if (!token) {
        setMessage("Token not found in response");
        return;
      }

      localStorage.setItem("token", token);

      setMessage("Login success");

      navigate("/dashboard");
    } catch (error) {
      setMessage("Backend not running or network error");
    }
  }

  return (
    <div className="auth-page">
      <div className="auth-card">
        <p className="auth-tag">Welcome back</p>

        <h1>Login to CourseFlow</h1>

        <p className="auth-subtitle">
          Enter your credentials to access your course dashboard.
        </p>

        <form className="auth-form" onSubmit={handleLogin}>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
          </div>

          {message && <p className="form-message">{message}</p>}

          <button type="submit" className="auth-btn">
            Login
          </button>
        </form>

        <p className="auth-footer">
          New user? <Link to="/register">Create account</Link>
        </p>

        <Link to="/" className="back-link">
          ← Back to Home
        </Link>
      </div>
    </div>
  );
}

export default Login;