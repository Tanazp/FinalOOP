import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';
import CreateCourse from './components/CreateCourse';
import EditCourse from './components/EditCourse';
import SearchCourses from './components/SearchCourses';
import Notifications from './components/Notifications';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';

function App() {
    return (
        <Router>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" style={{ flexGrow: 1 }}>
                        Academic Exchange Platform
                    </Typography>
                    <Button color="inherit" component={Link} to="/login">Login</Button>
                    <Button color="inherit" component={Link} to="/register">Register</Button>
                    <Button color="inherit" component={Link} to="/dashboard">Dashboard</Button>
                </Toolbar>
            </AppBar>

            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/create-course" element={<CreateCourse />} />
                <Route path="/edit-course/:id" element={<EditCourse />} />
                <Route path="/search-courses" element={<SearchCourses />} />
                <Route path="/notifications" element={<Notifications />} />
                <Route path="/" element={<Login />} />
            </Routes>
        </Router>
    );
}

export default App;

