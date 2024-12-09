import React from 'react';
import { Route, Routes, Link } from 'react-router-dom';
import CreateCourse from './CreateCourse';
import EditCourse from './EditCourse';
import SearchCourses from './SearchCourses';
import Notifications from './Notifications';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';

const Dashboard = () => {
    return (
        <Box>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>

                    </Typography>
                    <Button color="inherit" component={Link} to="/create-course">Create Course</Button>
                    <Button color="inherit" component={Link} to="/search-courses">Search Courses</Button>
                    <Button color="inherit" component={Link} to="/notifications">Notifications</Button>
                    <Button color="inherit" component={Link} to="/logout">Logout</Button>
                </Toolbar>
            </AppBar>
            <Box sx={{ p: 3 }}>
                <Typography variant="h4">Welcome to the Dashboard</Typography>
            </Box>
        </Box>
    );
};

export default Dashboard;

