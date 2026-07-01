import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function Dashboard() {
  const navigate = useNavigate();

  const [courses, setCourses] = useState([]);
  const [search, setSearch] = useState("");
  const [message, setMessage] = useState("Loading courses...");

  const [showModal, setShowModal] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const [selectedCourseId, setSelectedCourseId] = useState(null);

  const [form, setForm] = useState({
    courseName: "",
    courseType: "",
    courseDuration: "",
    courseFee: "",
  });

  useEffect(() => {
    fetchCourses();
  }, []);

  async function fetchCourses() {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    try {
      const response = await fetch("/courses", {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.status === 401 || response.status === 403) {
        localStorage.removeItem("token");
        navigate("/login");
        return;
      }

      const result = await response.json();
      const courseList = result.data || result;

      setCourses(courseList);
      setMessage("");
    } catch (error) {
      setMessage("Unable to fetch courses. Please check backend.");
    }
  }

  function handleInputChange(event) {
    const { name, value } = event.target;

    setForm({
      ...form,
      [name]: value,
    });
  }

  function openAddModal() {
    setIsEditMode(false);
    setSelectedCourseId(null);

    setForm({
      courseName: "",
      courseType: "",
      courseDuration: "",
      courseFee: "",
    });

    setShowModal(true);
  }

  function handleEditClick(course) {
    setIsEditMode(true);
    setSelectedCourseId(course.id);

    setForm({
      courseName: course.courseName,
      courseType: course.courseType,
      courseDuration: course.courseDuration,
      courseFee: course.courseFee,
    });

    setShowModal(true);
  }

  async function handleSubmitCourse(event) {
    event.preventDefault();

    if (isEditMode) {
      await handleUpdateCourse();
    } else {
      await handleAddCourse();
    }
  }

  async function handleAddCourse() {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    try {
      const response = await fetch("/courses", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          courseName: form.courseName,
          courseType: form.courseType,
          courseDuration: Number(form.courseDuration),
          courseFee: Number(form.courseFee),
        }),
      });

      if (response.status === 403) {
        setMessage("Only ADMIN can add courses.");
        setShowModal(false);
        return;
      }

      if (!response.ok) {
        setMessage("Course add failed. Please check entered values.");
        return;
      }

      closeModal();
      fetchCourses();
    } catch (error) {
      setMessage("Backend not running or network error.");
    }
  }

  async function handleUpdateCourse() {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    try {
      const response = await fetch(`/courses/${selectedCourseId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          courseName: form.courseName,
          courseType: form.courseType,
          courseDuration: Number(form.courseDuration),
          courseFee: Number(form.courseFee),
        }),
      });

      if (response.status === 403) {
        setMessage("Only ADMIN can update courses.");
        setShowModal(false);
        return;
      }

      if (!response.ok) {
        setMessage("Course update failed. Please check entered values.");
        return;
      }

      closeModal();
      fetchCourses();
    } catch (error) {
      setMessage("Backend not running or network error.");
    }
  }

  async function handleDeleteCourse(id) {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this course?"
    );

    if (!confirmDelete) {
      return;
    }

    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    try {
      const response = await fetch(`/courses/${id}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.status === 403) {
        setMessage("Only ADMIN can delete courses.");
        return;
      }

      if (!response.ok) {
        setMessage("Course delete failed.");
        return;
      }

      fetchCourses();
    } catch (error) {
      setMessage("Backend not running or network error.");
    }
  }

  function closeModal() {
    setShowModal(false);
    setIsEditMode(false);
    setSelectedCourseId(null);

    setForm({
      courseName: "",
      courseType: "",
      courseDuration: "",
      courseFee: "",
    });
  }

  function handleLogout() {
    localStorage.removeItem("token");
    navigate("/login");
  }

  const filteredCourses = courses.filter((course) =>
    course.courseName?.toLowerCase().includes(search.toLowerCase())
  );

  const onlineCourses = courses.filter((course) =>
    course.courseType?.toLowerCase().includes("online")
  );

  return (
    <div className="dashboard-page">
      <aside className="sidebar">
        <Link to="/" className="dashboard-logo">
          CourseFlow
        </Link>

        <nav className="side-nav">
          <a href="#">Dashboard</a>
          <a href="#">Courses</a>
          <a href="#">Students</a>
          <a href="#">Reports</a>
        </nav>

        <button className="logout-btn" onClick={handleLogout}>
          Logout
        </button>
      </aside>

      <main className="dashboard-main">
        <section className="dashboard-hero">
          <div>
            <p className="dashboard-tag">Admin Dashboard</p>
            <h1>Manage your courses.</h1>
            <p>
              Create, update, search and manage all courses from one premium
              dashboard.
            </p>
          </div>

          <button className="add-course-btn" onClick={openAddModal}>
            + Add Course
          </button>
        </section>

        <section className="stats-grid">
          <div className="stat-card">
            <h3>{courses.length}</h3>
            <p>Total Courses</p>
          </div>

          <div className="stat-card">
            <h3>{courses.length}</h3>
            <p>Active Courses</p>
          </div>

          <div className="stat-card">
            <h3>{onlineCourses.length}</h3>
            <p>Online Courses</p>
          </div>
        </section>

        <section className="course-section">
          <div className="section-title">
            <h2>Available Courses</h2>

            <input
              type="text"
              placeholder="Search course..."
              value={search}
              onChange={(event) => setSearch(event.target.value)}
            />
          </div>

          {message && <p className="form-message">{message}</p>}

          <div className="course-grid">
            {filteredCourses.map((course) => (
              <div className="course-card" key={course.id}>
                <div className="course-top">
                  <span>{course.courseType}</span>
                  <button>⋯</button>
                </div>

                <h3>{course.courseName}</h3>

                <p className="course-duration">
                  {course.courseDuration} Months
                </p>

                <div className="course-bottom">
                  <strong>₹{course.courseFee}</strong>

                  <div className="course-actions">
                    <button
                      className="edit-btn"
                      onClick={() => handleEditClick(course)}
                    >
                      Edit
                    </button>

                    <button
                      className="delete-btn"
                      onClick={() => handleDeleteCourse(course.id)}
                    >
                      Delete
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>

          {filteredCourses.length === 0 && !message && (
            <p className="empty-message">No courses found.</p>
          )}
        </section>
      </main>

      {showModal && (
        <div className="modal-overlay">
          <div className="modal-card">
            <div className="modal-header">
              <div>
                <p className="auth-tag">
                  {isEditMode ? "Update Course" : "New Course"}
                </p>

                <h2>{isEditMode ? "Edit Course" : "Add Course"}</h2>
              </div>

              <button className="close-btn" onClick={closeModal}>
                ×
              </button>
            </div>

            <form className="auth-form" onSubmit={handleSubmitCourse}>
              <div className="form-group">
                <label>Course Name</label>
                <input
                  type="text"
                  name="courseName"
                  placeholder="Python Full Stack"
                  value={form.courseName}
                  onChange={handleInputChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Course Type</label>
                <input
                  type="text"
                  name="courseType"
                  placeholder="Online / Offline / Hybrid"
                  value={form.courseType}
                  onChange={handleInputChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Duration in Months</label>
                <input
                  type="number"
                  name="courseDuration"
                  placeholder="4"
                  value={form.courseDuration}
                  onChange={handleInputChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Fee</label>
                <input
                  type="number"
                  name="courseFee"
                  placeholder="16000"
                  value={form.courseFee}
                  onChange={handleInputChange}
                  required
                />
              </div>

              <button type="submit" className="auth-btn">
                {isEditMode ? "Update Course" : "Save Course"}
              </button>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default Dashboard;