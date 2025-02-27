package com.ohgiraffers.member.controller;

import com.ohgiraffers.member.model.dto.MemberDTO;
import com.ohgiraffers.member.service.MemberService;
import com.ohgiraffers.member.view.PrintResult;

import java.util.List;
import java.util.Map;

public class MemberController {

    private final PrintResult printResult;
    private final MemberService memberService;
    public MemberController() {
        printResult = new PrintResult();
        memberService = new MemberService();
    }

    public void selectAllMember() {

            List<MemberDTO> memberList = memberService.selectAllMember();

            if(memberList != null){
                printResult.printMemberList(memberList);
            }else{
                printResult.printErrorMessage("selectList");
            }


        }

    public void selectMemberByCode(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("memberCode"));
        System.out.println("code = " + code);

        MemberDTO member = MemberService.selectMemberByCode(code);
        if(member != null){
            printResult.printMember(member);
        }else{
            printResult.printErrorMessage("selectone");
        }

    }

    public void registMember(Map<String, String> parameter) {
        String memberName = parameter.get("memberName");
        String birthDate = parameter.get("birthDate");
        String detailInfo = parameter.get("detailInfo");
        String contact = parameter.get("contact");


        MemberDTO member = new MemberDTO();
        member.setMemberName(memberName);
        member.setBirthDate(birthDate);
        member.setDetailInfo(detailInfo);
        member.setContact(contact);

        System.out.println("member = " + member);

        if(memberService.insertMember(member)){
            printResult.printSuccessMessage("insert");

        }else{
            printResult.printErrorMessage("insert");
        }
    }

    public void modifyMember(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("memberCode"));

        String memberName = parameter.get("memberName");
        String birthDate = parameter.get("birthDate");
        String detailInfo = parameter.get("detailInfo");
        String contact = parameter.get("contact");


        MemberDTO member = new MemberDTO();
        member.setMemberCode(code);
        member.setMemberName(memberName);
        member.setBirthDate(birthDate);
        member.setDetailInfo(detailInfo);
        member.setContact(contact);

        System.out.println("member = " + member);

        if(memberService.modifyMember(member)){
            printResult.printSuccessMessage("update");

        }else{
            printResult.printErrorMessage("update");
        }

    }

    public void deleteMember(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("memberCode"));
        System.out.println("code = " + code);

        if(memberService.deleteMember(code)){
            printResult.printSuccessMessage("delete");
        }else{
            printResult.printErrorMessage("delete");
        }
    }
}
