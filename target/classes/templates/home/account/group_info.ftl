<input type="hidden" id="focus-view-group-id" value="${accountGroup.id}">
<input type="hidden" id="focus-view-group-admin-id" value="${accountGroup.admin.id}">
<div class="chat-user-head">
    <div class="row align-items-center">
        <div class="col-9">
            <h5>查看聊天室信息</h5>
        </div>
        <div class="col-3">
            <ul class="list-inline float-right mb-0">
                <li class="list-inline-item">
                    <a href="#" id="close-group-info"><i class="feather icon-x"></i></a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="chat-user-body" style="z-index: 1">
    <div class="userbar">
        <div>
            <img class="user-pic img-fluid" style="height: 100px" src="/photo/view?filename=${accountGroup.picture}" id="view-group-info-pic">
            <#if base_account.id == accountGroup.admin.id>
                <div style="position:relative;top:-30px;right:-40px;color:#8A98AC;" >
                    <i class="feather icon-camera" id="upload-group-img-btn" style="font-size: 20px;cursor:pointer;"></i>
                    <input class="profile-upload" type="file" style="display:none;" id="group-photo-file" onchange="uploadGroupPic()" accept="image/*"/>
                </div>
            </#if>
        </div>

        <h5 id="view-group-info-name">${accountGroup.name}</h5>
        <p class="mb-0">
            <span id="view-group-info-desc">${accountGroup.info!"这聊天室人太懒，啥也没写！"}</span>
            <#if base_account.id == accountGroup.admin.id>

                <span style="margin-left:5px;cursor:pointer;" title="点击修改聊天室简介" data-toggle="tooltip" id="edit-group-info-btn">
                <i class="feather icon-edit-2"></i>
            </span>
            </#if>
        </p>
    </div>
    <div class="user-detail">
        <p class="user-detail-header" style="padding-bottom:5px;margin-bottom:5px;">聊天室成员</p>
        <div class="added-users">
            <div class="avatar-group" id="show-chat-group-users" style="display:block;padding-left:15px;">
                <#list accountGroupMemberList as accountGroupMember>
                    <div class="avatar" style="margin-left:-0.8rem;position:relative;display:inline-block;width:35px;height:35px;font-size:1rem;transition:transform 0.2s;" aid="${accountGroupMember.id}">
                        <a href="#" data-toggle="tooltip" data-placement="top" title="${accountGroupMember.nickname}" data-original-title="${accountGroupMember.nickname}">
                            <#if base_account.id == accountGroupMember.member.id>
                                <img width="35px" style="border:1px solid #66f151;" nickname="${accountGroupMember.nickname}" member-id="${accountGroupMember.id}" msg-status="${accountGroupMember.msgStatus}" height="35px" src="/photo/view?filename=${accountGroupMember.member.headPic}" alt="avatar" class="rounded-circle me">
                            <#else>
                                <img width="35px" height="35px" src="/photo/view?filename=${accountGroupMember.member.headPic}" alt="avatar" class="rounded-circle">
                            </#if>
                        </a>
                    </div>
                </#list>
                <div class="avatar" style="position:relative;display:inline-block;width:35px;height:35px;font-size:1rem;transition:transform 0.2s;">
                    <a href="#" data-toggle="tooltip" id="add-group-member-btn" data-placement="top" title="添加成员" data-original-title="添加成员">
                        <img width="35px" height="35px" src="/home/images/add-member.svg" alt="avatar" class="rounded-circle">
                    </a>
                </div>
                <#if base_account.id == accountGroup.admin.id>
                    <div class="avatar" style="position:relative;display:inline-block;width:35px;height:35px;font-size:1rem;transition:transform 0.2s;">
                        <a href="#" data-toggle="tooltip" id="reduce-group-member-btn" data-placement="top" title="移除成员" data-original-title="移除成员">
                            <img width="35px" height="35px" src="/home/images/reduce-member.svg" alt="avatar" class="rounded-circle">
                        </a>
                    </div>
                </#if>
            </div>
        </div>
        <div class="user-about">
            <h6 style="font-weight:300;margin-bottom:4px;">聊天室名：</h6>
            <h6 style="font-weight:550;margin-bottom:12px;"><span id="show-group-name">${accountGroup.name}</span>
                <#if base_account.id == accountGroup.admin.id>
                    <span style="margin-left:5px;cursor:pointer;" title="点击修改聊天室名称" data-toggle="tooltip" id="edit-group-name-btn">
                    <i class="feather icon-edit-2"></i>
                </span>
                </#if>
            </h6>
            <h6 style="font-weight:300;margin-bottom:4px;">聊天室公告：</h6>
            <h6 style="font-weight:550;margin-bottom:12px;">
                <span id="show-group-notice">${accountGroup.notice!"暂无聊天室公告"}</span>
                <#if base_account.id == accountGroup.admin.id>
                    <span style="margin-left:5px;cursor:pointer;" title="点击修改聊天室公告" data-toggle="tooltip" id="edit-group-notice-btn">
            		<i class="feather icon-edit-2"></i>
            	</span>
                </#if>
            </h6>
            <h6 style="font-weight:300;margin-bottom:4px;">我在本聊天室的昵称：</h6>
            <h6 style="font-weight:550;margin-bottom:12px;">
                <span id="show-group-member-nickname"></span>
                <#if flag=="true">
                    <span style="margin-left:5px;cursor:pointer;" title="点击修改我的聊天室昵称" data-toggle="tooltip" id="edit-group-member-nickname-btn">
            		<i class="feather icon-edit-2"></i>
            	</span>
                </#if>
            </h6>
        </div>
        <p class="user-detail-header">聊天设置</p>
        <div class="user-setting" style="padding-bottom:0px;margin-bottom:0px;">
            <#--            <div class="row align-items-center pb-3">-->
            <#--                <div class="col-9"><h6 class="mb-0">消息免打扰</h6></div>-->
            <#--                <div class="col-3 text-right">-->
            <#--                    <div class="custom-control custom-switch">-->
            <#--                        <input type="checkbox" class="custom-control-input" id="group-msg-mute-notification-setting" onclick="updateGroupMember(false)">-->
            <#--                        <label class="custom-control-label" for="group-msg-mute-notification-setting"></label>-->
            <#--                    </div>-->
            <#--                </div>-->
            <#--            </div>-->
            <#if base_account.id == accountGroup.admin.id>

                <div class="row align-items-center pb-3">
                    <div class="col-9"><h6 class="mb-0">设置禁言 <span style="margin-left:5px;cursor:pointer;" title="点击设置禁言" data-toggle="tooltip" id="edit-mStatus-btn"><i class="feather icon-edit-2"></i></span></h6></div>
                </div>
                <div class="row align-items-center pb-3">
                    <div class="col-9"><h6 class="mb-0">解除禁言 <span style="margin-left:5px;cursor:pointer;" title="点击解除禁言" data-toggle="tooltip" id="a-edit-mStatus-btn"><i class="feather icon-edit-2"></i></span></h6></div>
                </div>
            </#if>
        </div>
        <p class="user-detail-header">高级设置</p>
        <div class="user-setting" style="padding-bottom:0px;margin-bottom:0px;">
            <#--            <div class="row align-items-center pb-3" style="text-align:center;">-->
            <#--                <div class="col-12"><input type="button" class="btn btn-warning" id="clear-all-group-msg" value="清空聊天记录" style="width:100%;"></div>-->
            <#--            </div>-->
            <#if base_account.id == accountGroup.admin.id>
                <div class="row align-items-center pb-3" style="text-align:center;">
                    <div class="col-12">
                        <#--                        <input type="button" class="btn btn-danger" id="distory-group-chat-btn" value="解散该聊天室" style="width:100%;">-->
                        <button type="button" id="distory-group-chat-btn" class="layui-btn layui-btn-fluid layui-btn-danger">解散该聊天室</button>

                    </div>
                </div>
                <div class="row align-items-center pb-3" style="text-align:center;">
                    <div class="col-12">
                        <#--                        <input type="button" id="start-group-chat-btn" class="btn btn-primary" value="立即发送消息" style="width:100%;">-->
                        <button type="button" id="start-group-chat-btn" class="layui-btn layui-btn-fluid">立即发送消息</button>

                    </div>
                </div>
            <#else>
                <div class="row align-items-center pb-3" style="text-align:center;">
                    <#if flag=="false">
                        <div class="col-12">
                            <input type="button" class="btn btn-danger" id="join-group-chat-btn" value="立即加入" style="width:100%;">
                        </div>
                    <#else>
                        <div class="col-12">
                            <#--                            <input type="button" class="btn btn-danger" id="out-group-chat-btn" value="退出该聊天室" style="width:100%;">-->
                            <button type="button" id="out-group-chat-btn" class="layui-btn layui-btn-fluid layui-btn-danger">退出该聊天室</button>

                        </div>
                    </#if>
                </div>
                <#if flag=="true">
                    <div class="row align-items-center pb-3" style="text-align:center;">
                        <div class="col-12">
                            <#--                            <input type="button" id="start-group-chat-btn" class="btn btn-primary" value="立即发送消息" style="width:100%;">-->
                            <button type="button" id="start-group-chat-btn" class="layui-btn layui-btn-fluid">立即发送消息</button>

                        </div>
                    </div>
                </#if>
            </#if>
            <#--            <div class="row align-items-center pb-3" style="text-align:center;">-->
            <#--            	<#if base_account.id == accountGroup.admin.id>-->
            <#--                <div class="col-12"><input type="button" class="btn btn-danger" id="distory-group-chat-btn" value="解散该聊天室" style="width:100%;"></div>-->
            <#--            	<#else>-->
            <#--                <div class="col-12"><input type="button" class="btn btn-danger" id="out-group-chat-btn" value="退出该聊天室" style="width:100%;"></div>-->
            <#--            	</#if>-->
            <#--            </div>-->
            <#--            <div class="row align-items-center pb-3" style="text-align:center;">-->
            <#--                <div class="col-12"><input type="button" id="start-group-chat-btn" class="btn btn-primary" value="立即发送消息" style="width:100%;"></div>-->
            <#--            </div>-->
        </div>
    </div>
</div>
<script>
    //修改聊天室名称
    $("#edit-group-name-btn").click(function(){
        var index = layer.prompt({title: '修改聊天室名称', formType: 3}, function(text, index){
            layer.close(index);
            ajaxRequest('update_group_info','post',{id:${accountGroup.id},name:text},function(data){
                layer.msg('修改成功！');
                $("#show-group-name").text(text);
                $("#view-group-info-name").text(text);
                $("#new-group-chat-${accountGroup.id}-tab").find("h5").html(text+'<sup style="color:red;">【聊天室】</sup>');
            },function(data){
                layer.msg(data.msg);
            });
        });
        $('#layui-layer'+index + " .layui-layer-input").val($("#show-group-name").text());
    })
    //修改聊天室公告
    $("#edit-group-notice-btn").click(function(){
        var index = layer.prompt({title: '修改聊天室公告', formType: 2}, function(text, index){
            layer.close(index);
            ajaxRequest('update_group_info','post',{id:${accountGroup.id},notice:text},function(data){
                layer.msg('修改成功！');
                $("#show-group-notice").text(text);
            },function(data){
                layer.msg(data.msg);
            });
        });
        $('#layui-layer'+index + " .layui-layer-input").val($("#show-group-notice").text());
    })
    //修改聊天室简介
    $("#edit-group-info-btn").click(function(){
        var index = layer.prompt({title: '修改聊天室简介', formType: 2}, function(text, index){
            layer.close(index);
            ajaxRequest('update_group_info','post',{id:${accountGroup.id},info:text},function(data){
                layer.msg('修改成功！');
                $("#view-group-info-desc").text(text);
                $("#new-group-chat-${accountGroup.id}-tab").find("p").text(text);
            },function(data){
                layer.msg(data.msg);
            });
        });
        $('#layui-layer'+index + " .layui-layer-input").val($("#view-group-info-desc").text());
    })
    //修改聊天室里的昵称
    $("#edit-group-member-nickname-btn").click(function(){
        updateGroupMember(true);
    })
    //关闭聊天室查看界面
    $("#close-group-info").click(function(){
        $("#view-chat-group-info").removeClass("show");
        $(".chat-bottom").removeClass("small");
    });
    //给昵称赋值
    $("#show-group-member-nickname").text($("img.me").attr('nickname'));
    $("#group-msg-mute-notification-setting").prop('checked',($("img.me").attr('msg-status') == '0'));
    //更新聊天室成员信息
    function updateGroupMember(isNickname){
        if(isNickname){
            var index = layer.prompt({title: '修改我在聊天室里的昵称', formType: 3}, function(text, index){
                layer.close(index);
                var postData = {accountGroupId:${accountGroup.id},nickname:text};
                postData.msgStatus = $("#group-msg-mute-notification-setting").prop('checked') ? 0 : 1;
                ajaxRequest('update_group_member_info','post',postData,function(data){
                    layer.msg('修改成功！');
                    $("#show-group-member-nickname").text(text);
                },function(data){
                    layer.msg(data.msg);
                });
            });
            $('#layui-layer'+index + " .layui-layer-input").val($("#show-group-member-nickname").text());
            return;
        }
        var postData = {accountGroupId:${accountGroup.id},nickname:$("#show-group-member-nickname").text()};
        postData.msgStatus = $("#group-msg-mute-notification-setting").prop('checked') ? 0 : 1;
        ajaxRequest('update_group_member_info','post',postData,function(data){
            layer.msg('修改成功！');
        },function(data){
            layer.msg(data.msg);
        });
    }
    //修改聊天室封面
    //上传图片
    function uploadGroupPic(){
        if($("#group-photo-file").val() == '')return;
        uploadFileToServer('group-photo-file',function(data){
            layer.msg('图片上传成功');
            var filename = data.data;
            ajaxRequest('update_group_info','post',{id:${accountGroup.id},picture:filename},function(data){
                $("#view-group-info-pic").attr('src','/photo/view?filename='+filename);
                $("#new-group-chat-${accountGroup.id}-tab").find("img").attr('src','/photo/view?filename='+filename);
                $("#group-photo-file").val('');
            },function(data){
                layer.msg(data.msg);
            });

        },function(data){
            if(!isJson(data)){
                data = $.parseJSON(data);
            }
            layer.msg(data.msg);
        });
    }
    //点击修改封面按钮
    $("#upload-group-img-btn").click(function(){
        $("#group-photo-file").click();
    });
    //清空聊天记录按钮
    $("#clear-all-group-msg").click(function(){
        //询问框
        layer.confirm('确定清空？（清空后不能恢复哦！）', {
            btn: ['确定','再想想'] //按钮
        }, function(){
            clearMsg(${accountGroup.id},'group');
        }, function(){

        });

    });
    //添加成员
    $("#add-group-member-btn").click(function(){
        removeGroupMemberListEvent();
        $.get('group_member_list',{accountGroupId:${accountGroup.id},from:0},function(data,status,xhr){
            if(data == ''){
                layer.msg('你真可怜，没有一个好友！');
            }
            $("#show-friend-user-list").html(data);
        });
        $("#add-group-user").modal('toggle');
    })
    //移除成员
    $("#reduce-group-member-btn").click(function(){
        removeGroupMemberListEvent();
        $.get('group_member_list',{accountGroupId:${accountGroup.id},from:1},function(data,status,xhr){
            if(data == ''){
                layer.msg('没有一个成员！');
            }
            $("#show-group-user-list").html(data);
        });
        $("#reduce-group-user").modal('toggle');
    })
    //设置禁言
    $("#edit-mStatus-btn").click(function(){
        removeGroupMemberListEvent();
        $.get('group_member_list',{accountGroupId:${accountGroup.id},from:2},function(data,status,xhr){
            if(data == ''){
                layer.msg('没有一个成员！');
            }
            $("#show-mStatus-user-list").html(data);
        });
        $("#reduce-mStatus-user").modal('toggle');
    })
    //解除禁言
    $("#a-edit-mStatus-btn").click(function(){
        removeGroupMemberListEvent();
        $.get('group_member_list',{accountGroupId:${accountGroup.id},from:3},function(data,status,xhr){
            if(data == ''){
                layer.msg('没有一个成员！');
            }
            $("#a-show-mStatus-user-list").html(data);
        });
        $("#a-reduce-mStatus-user").modal('toggle');
    })
    //退出聊天室
    $("#out-group-chat-btn").click(function(){
        //询问框
        layer.confirm('确定退出该聊天室？', {
            btn: ['确定','再想想'] //按钮
        }, function(){
            var accountGroupId = $("#focus-view-group-id").val()
            var postData = {mids:$("img.me").attr('member-id'),type:0,accountGroupId:accountGroupId};
            ajaxRequest('update_group_member','post',postData,function(data){
                layer.msg('退出成功！');
                $("#new-group-chat-"+accountGroupId+"-tab").remove();
                $("#group-chat-"+accountGroupId+"-tab").remove();
                $("#close-group-info").trigger('click');
                var allChatTapInfo = getALLChatTapInfoFromLocal();
                delete allChatTapInfo['group_'+accountGroupId];
                setAllChatTapInfoToLocal(allChatTapInfo);
            },function(data){
                layer.msg(data.msg);
            });
        }, function(){

        });
    })
    //解散聊天室
    $("#distory-group-chat-btn").click(function(){
        //询问框
        layer.confirm('确定解散该聊天室？', {
            btn: ['确定','再想想'] //按钮
        }, function(){
            var accountGroupId = $("#focus-view-group-id").val()
            var postData = {accountGroupId:accountGroupId};
            ajaxRequest('delete_group','post',postData,function(data){
                layer.msg('解散成功！');
                $("#new-group-chat-"+accountGroupId+"-tab").remove();
                $("#close-group-info").trigger('click');
            },function(data){
                layer.msg(data.msg);
            });
        }, function(){

        });
    })
    //点击发消息到聊天室
    $("#start-group-chat-btn").click(function(){
        var groupId = ${accountGroup.id};
        var groupName = '${accountGroup.name}';
        var peopleNum = ${accountGroup.curPeople};
        var picture = '${accountGroup.picture}';
        var groupChat = $("#group-chat-"+groupId+"-tab")
        if(groupChat.length == 0){
            var option = {};
            option.groupId = groupId;
            option.groupName = groupName;
            option.peopleNum = peopleNum;
            option.picture = picture;
            option.lastMsg = '';
            option.flag = 'false';
            option.type = 'group';
            option.time = dateFormat("HH:MM", new Date());
            createGroupChatTab(option,true);
        }
        $("#pills-chat-tab-justified").trigger('click');
        $("#close-group-info").trigger('click');
        $('#group-chat-'+groupId+'-tab').trigger('click');
    })

    //点击立即加入请求按钮
    $("#join-group-chat-btn").click(function(){
        var accountGroupId = $("#focus-view-group-id").val()
        var adminId = $("#focus-view-group-admin-id").val()
        layer.prompt({title: '加聊天室备注信息', formType: 2}, function(text, index){
            layer.close(index);
            ajaxRequest('add_group_request','post',{accountIds:accountGroupId,adminId:adminId,remark:text},function(data){
                layer.msg('发送请求成功，等待管理员通过！');
                // $("#addUser").modal("toggle");
            },function(data){
                layer.msg(data.msg);
            });
        });
    });
</script>
