import React, { useState, useEffect, useRef } from 'react';
import { useNavigate, Link,useLocation } from 'react-router-dom';
import '../../common.css';
import './SignIn.css';
import AuthPageTitle from '../../components/AuthPageTitle/AuthPageTitle';
import { useAuthState } from '../../atoms';
import { signIn } from '../../services/authApi'

function Signin() {
    const [authState, setAuthState] = useAuthState()
    const [errMsg, setErrMsg] = useState('')
    const [userData, setUserData] = useState({
        identifier: '',
        password: ''
    })

    //useRef
    const emailInputRef = useRef(null)

    // useEffect
    useEffect(() => {
        //auto input focus
        emailInputRef.current.focus();
    }, []);

    // handlers
    const handleSubmit = async (e) => {
        e.preventDefault();

        signIn(userData).then(data => {
            setAuthState({ isLoggedIn: true, userData: data })
        }).catch((error) => {
            alert("유저데이터 가져오기 실패")
        })
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserData({ ...userData, [name]: value });
        setErrMsg('')
    }

    //button disabled utill fill all of inputs
    const filledInputchk = Object.values(userData).indexOf("") == -1 ? "" : "disabled"


    return (
        <div className='auth-container'>
            <AuthPageTitle title='로그인' />
            <form className='auth-form' onSubmit={handleSubmit}>
                {errMsg && <p className='errmsg'>{errMsg}</p>}
                <label>
                    이메일
                    <input id="email" className='auth-input' name="identifier" type="email" ref={emailInputRef} autoComplete="email" onChange={handleChange} />
                </label>
                <label>
                    비밀번호
                    <input id="password" className='auth-input' name="password" type="password" onChange={handleChange} />
                </label>
                <button disabled={filledInputchk} type='submit' className={`${filledInputchk} auth-btn`}>로그인</button>
            </form>
            <div className='login-footer'>
                <Link to="/auth/forgot-password"><div>비밀번호 찾기</div></Link>
                &nbsp;|&nbsp;
                <Link to="/auth/register"><div>회원가입</div></Link>
            </div>
        </div >


    )
}

export default Signin