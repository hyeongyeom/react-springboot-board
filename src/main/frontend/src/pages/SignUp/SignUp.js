import React, { useState, useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { GoAlert } from 'react-icons/go';
import AuthPageTitle from '../../components/AuthPageTitle/AuthPageTitle';
import '../../common.css';
import './SignUp.css';
import {signUp,getUserByNickname } from '../../services/authApi'

function Signup() {
// firebase auth

// useState
    const [nickname,setNickname]=useState('')
    const [nickNameChkMsg,setnickNameChkMsg]=useState('')
    const [isNicnameDuplicated, setIsNicnameDuplicated] = useState(false)
    const [isDupleClicked, setisDupleClicked] = useState(true)
    const [errMsg, setErrMsg] = useState('')

//useRef
    const emailInputRef = useRef(null)

//react-hook-form
    const {
        register,
        handleSubmit,
        formState: { errors },
        reset,
        trigger,
        watch,
        setFocus
    } = useForm();
    const password = watch('password')

// useEffect
    useEffect(() => {
        setFocus("email")

    }, []);

//navigate-redirection
    const navigate = useNavigate();

// hadlers
    const handleChange = (e) => {
        const nickname = e.target.value;
        setNickname(nickname)
        setisDupleClicked(true)
        setIsNicnameDuplicated(false)
        setnickNameChkMsg('')
    }
    // *닉네임 중복 체크*
    const nickDuplicateChk = async () => {
        getUserByNickname(nickname).then(data => {
            if (!data) {
                setIsNicnameDuplicated(true)
                setnickNameChkMsg('이미 다른 사용자가 사용 중입니다.')
            } else {
                setisDupleClicked(false)
                setIsNicnameDuplicated(false)
                setnickNameChkMsg('사용가능한 닉네임입니다.')
            }
        }).catch((err) => {
            //console.log(err)
        })
    }
    // *회원가입 제출*
    const onValid = async (data) => {
        setErrMsg("")
        if (isDupleClicked) {
            return setErrMsg("닉네임 중복체크를 완료해주세요!")
        }
        const userData = {
            username:data['nickname'],
            email: data['email'],
            password: data['password']
        }
        signUp(userData).then(data => {
            localStorage.setItem('isLoggedIn', 'true')
            localStorage.setItem('userData', JSON.stringify(data))
            navigate('/WelcomeSignedUpPage')
        }).catch((err) => {
            alert(err)
        })
    }

    return (
        <div className='auth-container'>
            <AuthPageTitle title='회원가입' />

            <form className='auth-form' onSubmit={handleSubmit(onValid)}>
                {errMsg && <p className='errmsg'>{errMsg}</p>}
                <label className='form-label'>
                    이메일
                    <input
                        id="email"
                        type="email"
                        className={`auth-input ${errors.email && "invalid"}`}
                        ref={emailInputRef}
                        autoComplete="email"
                        {...register("email", {
                            required: "이메일을 적어주세요.",
                            pattern: {
                                value: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                                message:"이메일 형식이 맞지 않습니다."
                            }
                        })}
                        onKeyUp={() => {
                            trigger("email")
                        }}
                    />
                </label>
                {errors.email && (<small className='text-danger'><GoAlert />{errors.email.message}</small>)}

                <label className='form-label'>
                    비밀번호
                    <input
                        id="password"
                        className={`auth-input ${errors.password && "invalid"}`}
                        type="password"
                        {...register("password", {
                            required: "비밀번호를 적어주세요.",
                            minLength: {
                                value: 8,
                                message:"8자 이상 적어주세요."
                            },
                            maxLength: {
                                value: 20,
                                message:"20자 까지만 적어주세요."
                            }
                        })}
                        onKeyUp={() => {
                            trigger("password")
                        }}
                    />
                </label>
                {errors.password && (<small className='text-danger'><GoAlert /> {errors.password.message }</small>)}

                <label className='form-label'>
                    비밀번호 확인
                    <input
                        id="confirmPassword"
                        className={`auth-input ${errors.confirmPassword && "invalid"}`}
                        type="password"
                        {...register("confirmPassword", {
                            required: true,
                            validate: (value) =>
                                value === password
                        })}
                        onKeyUp={() => {
                            trigger("confirmPassword")
                        }}
                    />
                </label>
                {errors.confirmPassword && errors.confirmPassword.type === 'required' && (
                    <small className='text-danger'><GoAlert /> 비밀번호를 확인해주세요. </small>
                )}
                {errors.confirmPassword && errors.confirmPassword.type === 'validate' && (
                    <small className='text-danger'><GoAlert /> 비밀번호가 일치하지 않습니다.</small>
                )}
                <label className='form-label'>
                    닉네임
                    <input
                        id="nickname"
                        type="text"
                        className={`auth-input ${errors.nickname && "invalid"}`}

                        {...register("nickname", {
                            required: "닉네임을 적어주세요.",
                            onChange:(e)=>handleChange(e),
                            pattern: {
                                value: /^[a-zA-Zㄱ-힣0-9]*$/,
                                message: "닉네임 형식이 맞지 않습니다(특수문자 x)."
                            },
                            minLength: {
                                value: 3,
                                message:"3자 이상 적어주세요."
                            },
                            maxLength: {
                                value: 6,
                                message:"6자 까지만 적어주세요."
                            }
                        })}
                        onKeyUp={() => {
                            trigger("nickname")
                        }}
                    />
                </label>
                <div className='nicknameDuple'>
                    <button type="button" className='btn-duplechk' onClick={nickDuplicateChk}>중복체크</button>
                    {errors.nickname ? (<small className='text-danger'><GoAlert /> {errors.nickname.message}</small>)
                        : isNicnameDuplicated ? (<small className='nicknameDupleTrue'>{nickNameChkMsg}</small>)
                            : (<small className='nicknameDupleFalse'>{nickNameChkMsg}</small>)}

                </div>
                <button type='submit' className='auth-btn'>동의하고 회원가입</button>
                {/* tos -terms of service */}
                <p className='tos'>서비스 이용 약관, 개인정보처리방침 내용을 확인하였고 동의합니다.</p>
            </form>
        </div>
    )
}

export default Signup