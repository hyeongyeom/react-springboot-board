import React, { useEffect } from 'react'
import './App.css';
import Signup from './pages/SignUp/SignUp';
import Signin from './pages/SignIn/SignIn';
import Home from './pages/Home/Home';
import NotFoundPage from './pages/NotFoundPage/NotFoundPage';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { useAuthState } from './atoms'
function App() {

  return (
      <div id='content-body'>

        <Router>
          <Routes>
            <Route path="*" element={<NotFoundPage />}>not found</Route>
            <Route path="/" element={<Home />} />
              <Route path="/login" element={ <Signin />} />
              <Route path="/register" element={<Signup />} />
          </Routes>
        </Router>
      </div>
  )
}

export default App;
