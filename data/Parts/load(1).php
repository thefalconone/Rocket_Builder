var mwPerformance=(window.performance&&performance.mark)?performance:{mark:function(){}},mwNow=(function(){var perf=window.performance,navStart=perf&&perf.timing&&perf.timing.navigationStart;return navStart&&typeof perf.now==='function'?function(){return navStart+perf.now();}:function(){return Date.now();};}()),mediaWikiLoadStart;function isCompatible(str){var ua=str||navigator.userAgent;return!!((function(){'use strict';return!this&&!!Function.prototype.bind&&!!window.JSON;}())&&'querySelector'in document&&'localStorage'in window&&'addEventListener'in window&&!(ua.match(/webOS\/1\.[0-4]|SymbianOS|Series60|NetFront|Opera Mini|S40OviBrowser|MeeGo|Android.+Glass|^Mozilla\/5\.0 .+ Gecko\/$|googleweblight/)||ua.match(/PlayStation/i)));}(function(){var NORLQ,script;if(!isCompatible()){document.documentElement.className=document.documentElement.className.replace(/(^|\s)client-js(\s|$)/,'$1client-nojs$2');NORLQ=window.NORLQ||[];while(NORLQ.length){NORLQ.shift()();}window.NORLQ={push:function(
fn){fn();}};window.RLQ={push:function(){}};return;}function startUp(){mw.config=new mw.Map(true);mw.loader.addSource({"local":"/load.php"});mw.loader.register([["site","0vrvbrq",[1]],["site.styles","1k32495",[],"site"],["noscript","17w1n7r",[],"noscript"],["filepage","1l1ea3w"],["user.groups","01prjnq",[5]],["user","0kjhdz2",[6],"user"],["user.styles","1m96pp8",[],"user"],["user.defaults","1muia56"],["user.options","0j3lz3q",[7],"private"],["user.tokens","1jk1kbq",[],"private"],["mediawiki.language.data","0msaucq",[177]],["mediawiki.skinning.elements","1uyvmd7"],["mediawiki.skinning.content","1obt6kj"],["mediawiki.skinning.interface","1foqotj"],["mediawiki.skinning.content.parsoid","0fx1mo7"],["mediawiki.skinning.content.externallinks","15ntjfx"],["jquery.accessKeyLabel","1vwg14u",[25,134]],["jquery.appear","1veww05"],["jquery.async","0zgd00p"],["jquery.autoEllipsis","0qf1vey",[37]],["jquery.badge","0guuph5",[174]],["jquery.byteLength","0tylodk"],["jquery.byteLimit","0ou1hx4",[21]],[
"jquery.checkboxShiftClick","1dmjdmz"],["jquery.chosen","1ats94k"],["jquery.client","19ygyqi"],["jquery.color","043dhuh",[27]],["jquery.colorUtil","0wom2kk"],["jquery.confirmable","13a5g0r",[178]],["jquery.cookie","1t0e10y"],["jquery.expandableField","1vz2t1s"],["jquery.farbtastic","1y43nfn",[27]],["jquery.footHovzer","1lbewze"],["jquery.form","0yoo5bb"],["jquery.fullscreen","02fkk3j"],["jquery.getAttrs","1wnffv4"],["jquery.hidpi","1rtipwc"],["jquery.highlightText","15kh5s0",[134]],["jquery.hoverIntent","0evb96p"],["jquery.i18n","0xxz6ig",[176]],["jquery.localize","0nenbt0"],["jquery.makeCollapsible","13fbeh4"],["jquery.mockjax","03lp6oa"],["jquery.mw-jump","001pduw"],["jquery.mwExtension","1c6q37m"],["jquery.placeholder","0ok1z4k"],["jquery.qunit","1s1pmtg"],["jquery.spinner","1aps5q7"],["jquery.jStorage","17g2fa7"],["jquery.suggestions","0a48fe8",[37]],["jquery.tabIndex","01uhqst"],["jquery.tablesorter","1xnv3jl",[134,179]],["jquery.textSelection","11vqf0h",[25]],[
"jquery.throttle-debounce","1po9r86"],["jquery.xmldom","0ly5zh5"],["jquery.tipsy","0sg5lzv"],["jquery.ui.core","0vvg3z0",[57],"jquery.ui"],["jquery.ui.core.styles","03kjh3g",[],"jquery.ui"],["jquery.ui.accordion","0hdy0gm",[56,76],"jquery.ui"],["jquery.ui.autocomplete","0i7f1pb",[65],"jquery.ui"],["jquery.ui.button","02v637m",[56,76],"jquery.ui"],["jquery.ui.datepicker","0lnjq39",[56],"jquery.ui"],["jquery.ui.dialog","1hsx6e1",[60,63,67,69],"jquery.ui"],["jquery.ui.draggable","1qckgsw",[56,66],"jquery.ui"],["jquery.ui.droppable","0boya1p",[63],"jquery.ui"],["jquery.ui.menu","0fmwmyk",[56,67,76],"jquery.ui"],["jquery.ui.mouse","0etmlbo",[76],"jquery.ui"],["jquery.ui.position","17aygqd",[],"jquery.ui"],["jquery.ui.progressbar","166or5m",[56,76],"jquery.ui"],["jquery.ui.resizable","09lr6l3",[56,66],"jquery.ui"],["jquery.ui.selectable","1k6ojho",[56,66],"jquery.ui"],["jquery.ui.slider","04hm7o0",[56,66],"jquery.ui"],["jquery.ui.sortable","05tbi5o",[56,66],"jquery.ui"],["jquery.ui.spinner",
"0wlyli8",[60],"jquery.ui"],["jquery.ui.tabs","1tdnhxv",[56,76],"jquery.ui"],["jquery.ui.tooltip","10l31sb",[56,67,76],"jquery.ui"],["jquery.ui.widget","1d1exgr",[],"jquery.ui"],["jquery.effects.core","1gicehz",[],"jquery.ui"],["jquery.effects.blind","1wuoklq",[77],"jquery.ui"],["jquery.effects.bounce","06dpp1e",[77],"jquery.ui"],["jquery.effects.clip","1q7f44b",[77],"jquery.ui"],["jquery.effects.drop","038dx3v",[77],"jquery.ui"],["jquery.effects.explode","00218wk",[77],"jquery.ui"],["jquery.effects.fade","0j7svuq",[77],"jquery.ui"],["jquery.effects.fold","1p1guwu",[77],"jquery.ui"],["jquery.effects.highlight","0wq7v5s",[77],"jquery.ui"],["jquery.effects.pulsate","1tf17c8",[77],"jquery.ui"],["jquery.effects.scale","034eyow",[77],"jquery.ui"],["jquery.effects.shake","16z0mpf",[77],"jquery.ui"],["jquery.effects.slide","0z5nqrg",[77],"jquery.ui"],["jquery.effects.transfer","1i1emru",[77],"jquery.ui"],["json","01prjnq"],["moment","0cypte7",[174]],["mediawiki.apihelp","0iw5nky"],[
"mediawiki.template","0wfswbf"],["mediawiki.template.mustache","1j98ynz",[94]],["mediawiki.template.regexp","0vv00th",[94]],["mediawiki.apipretty","19wxbbl"],["mediawiki.api","0m8ie8c",[151,9]],["mediawiki.api.category","166m64u",[139,98]],["mediawiki.api.edit","0bbfhjc",[139,98]],["mediawiki.api.login","1w64156",[98]],["mediawiki.api.options","1edkqng",[98]],["mediawiki.api.parse","1lpxjou",[98]],["mediawiki.api.upload","1lv2gew",[100]],["mediawiki.api.user","1pijmrg",[98]],["mediawiki.api.watch","1xhkf2l",[98]],["mediawiki.api.messages","0zxymg0",[98]],["mediawiki.api.rollback","0zkte4a",[98]],["mediawiki.content.json","0yzw4lv"],["mediawiki.confirmCloseWindow","1746e1i"],["mediawiki.debug","07rnn0o",[32]],["mediawiki.diff.styles","16m3oft"],["mediawiki.feedback","0f51y1h",[139,128,269]],["mediawiki.feedlink","19onu6c"],["mediawiki.filewarning","0pra8qq",[265]],["mediawiki.ForeignApi","0n9efzc",[117]],["mediawiki.ForeignApi.core","1u7i614",[98,261]],["mediawiki.helplink","18ksj98"],[
"mediawiki.hidpi","12zo7te",[36],null,null,"return'srcset'in new Image();"],["mediawiki.hlist","1xm74ce"],["mediawiki.htmlform","0aacok1",[22,134]],["mediawiki.htmlform.checker","1drlfed"],["mediawiki.htmlform.ooui","0brcmah",[265]],["mediawiki.htmlform.styles","1orao9w"],["mediawiki.htmlform.ooui.styles","0xtrzys"],["mediawiki.icon","044w4k7"],["mediawiki.inspect","0v3e5ly",[21,134]],["mediawiki.messagePoster","1xavf0d",[116]],["mediawiki.messagePoster.wikitext","0l9rnrq",[100,128]],["mediawiki.notification","130p5sp",[187]],["mediawiki.notify","0enebym"],["mediawiki.notification.convertmessagebox","1a80zvz",[130]],["mediawiki.notification.convertmessagebox.styles","1cq0n64"],["mediawiki.RegExp","0tro5u8"],["mediawiki.pager.tablePager","0rvcfdu"],["mediawiki.searchSuggest","0dgpden",[35,49,98]],["mediawiki.sectionAnchor","06xjiiu"],["mediawiki.storage","1yqupnv"],["mediawiki.Title","1ujmafv",[21,151]],["mediawiki.Upload","1va1n3a",[104]],["mediawiki.ForeignUpload","0gr9gq0",[116,140]]
,["mediawiki.ForeignStructuredUpload.config","1fvsh13"],["mediawiki.ForeignStructuredUpload","0j8wxet",[142,141]],["mediawiki.Upload.Dialog","0ghh8ep",[145]],["mediawiki.Upload.BookletLayout","1uvre20",[140,178,149,258,92,267,269,275,276]],["mediawiki.ForeignStructuredUpload.BookletLayout","0opogtv",[143,145,107,182,250,247]],["mediawiki.toc","0xywmh7",[155]],["mediawiki.Uri","0wslre5",[151,96]],["mediawiki.user","0opn8jj",[105,155,138,8]],["mediawiki.userSuggest","036qzbx",[49,98]],["mediawiki.util","0eu9ws8",[16,131]],["mediawiki.viewport","1bzkdpx"],["mediawiki.checkboxtoggle","0fcbcp9"],["mediawiki.checkboxtoggle.styles","00xlqg3"],["mediawiki.cookie","1txxpic",[29]],["mediawiki.toolbar","177egee",[52]],["mediawiki.experiments","1a4p0ec"],["mediawiki.action.edit","14kd1e5",[22,52,159,98]],["mediawiki.action.edit.styles","1o32tdw"],["mediawiki.action.edit.collapsibleFooter","124ux2l",[41,126,138]],["mediawiki.action.edit.preview","1d2ufos",[33,47,52,98,112,178]],[
"mediawiki.action.history","19khrfh"],["mediawiki.action.history.styles","1v6tno4"],["mediawiki.action.history.diff","16m3oft"],["mediawiki.action.view.dblClickEdit","04lh09j",[187,8]],["mediawiki.action.view.metadata","1yw86t4"],["mediawiki.action.view.categoryPage.styles","0od3bit"],["mediawiki.action.view.postEdit","1qnidez",[155,178,94]],["mediawiki.action.view.redirect","10q2nw3",[25]],["mediawiki.action.view.redirectPage","0cojdze"],["mediawiki.action.view.rightClickEdit","0krmtbf"],["mediawiki.action.edit.editWarning","1od33ss",[52,110,178]],["mediawiki.action.view.filepage","1gfli0n"],["mediawiki.language","0c2upao",[175,10]],["mediawiki.cldr","050tx5b",[176]],["mediawiki.libs.pluralruleparser","1dpbtsl"],["mediawiki.language.init","0thcjrw"],["mediawiki.jqueryMsg","12f42g4",[174,151,8]],["mediawiki.language.months","0xdz0j7",[174]],["mediawiki.language.names","0k5cyce",[177]],["mediawiki.language.specialCharacters","1lltfve",[174]],["mediawiki.libs.jpegmeta","1i0e2e2"],[
"mediawiki.page.gallery","0t3r4sf",[53,184]],["mediawiki.page.gallery.styles","0sovi6g"],["mediawiki.page.gallery.slideshow","0epfzj3",[139,98,267,283]],["mediawiki.page.ready","05bxwq3",[16,23,43]],["mediawiki.page.startup","01h9tsf",[151]],["mediawiki.page.patrol.ajax","04j091q",[47,139,98,187]],["mediawiki.page.watch.ajax","0qm6bzb",[139,106,178,187]],["mediawiki.page.rollback","0mnd4n5",[47,108]],["mediawiki.page.image.pagination","0jpbeht",[47,151]],["mediawiki.rcfilters.filters.base.styles","0eoqt15"],["mediawiki.rcfilters.filters.dm","0ai8w2z",[148,261]],["mediawiki.rcfilters.filters.ui","1n4ry72",[193,149,264,277,279,280,284]],["mediawiki.special","0ayg3w8"],["mediawiki.special.apisandbox.styles","0kypadt"],["mediawiki.special.apisandbox","1ttff7x",[98,178,248,264]],["mediawiki.special.block","08lmg2b",[121,151]],["mediawiki.special.changecredentials.js","0iyqe76",[98,123]],["mediawiki.special.changeslist","11hh7fu"],["mediawiki.special.changeslist.legend","0mld9lx"],[
"mediawiki.special.changeslist.legend.js","0qgkg57",[41,155]],["mediawiki.special.changeslist.enhanced","1ee96x4"],["mediawiki.special.changeslist.visitedstatus","045arfx"],["mediawiki.special.comparepages.styles","0971cvg"],["mediawiki.special.edittags","02e4jvr",[24]],["mediawiki.special.edittags.styles","0nhx8vz"],["mediawiki.special.import","1t1gpu6"],["mediawiki.special.movePage","1cr6xhn",[245]],["mediawiki.special.movePage.styles","1sbnol7"],["mediawiki.special.pageLanguage","092dcjz",[265]],["mediawiki.special.pagesWithProp","1qm5tyo"],["mediawiki.special.preferences","11lcqjm",[110,174,132]],["mediawiki.special.userrights","1pa4mk4",[132]],["mediawiki.special.preferences.styles","0un71u4"],["mediawiki.special.recentchanges","1jlx5og"],["mediawiki.special.search","170e5u4",[256]],["mediawiki.special.search.styles","00zwvph"],["mediawiki.special.search.interwikiwidget.styles","0msx1zm"],["mediawiki.special.search.commonsInterwikiWidget","12hw73n",[148,178]],[
"mediawiki.special.undelete","0li2ip2"],["mediawiki.special.upload","0fuiplw",[47,139,98,110,178,182,223,94]],["mediawiki.special.upload.styles","1l7prmf"],["mediawiki.special.userlogin.common.styles","0cq89bf"],["mediawiki.special.userlogin.signup.styles","11y1ae0"],["mediawiki.special.userlogin.login.styles","1f8o9xx"],["mediawiki.special.userlogin.signup.js","1p7n2x2",[53,98,122,178]],["mediawiki.special.unwatchedPages","0p2drhc",[139,106]],["mediawiki.special.watchlist","182h64y",[98,265,8]],["mediawiki.special.version","0956gwz"],["mediawiki.legacy.config","1e8dbr0"],["mediawiki.legacy.commonPrint","0j1nzre"],["mediawiki.legacy.protect","0go8p9g",[22]],["mediawiki.legacy.shared","1nxxnrl"],["mediawiki.legacy.oldshared","0r1ecq0"],["mediawiki.legacy.wikibits","0j4ckma"],["mediawiki.ui","0kjl4cg"],["mediawiki.ui.checkbox","0emqgqc"],["mediawiki.ui.radio","0dw61yg"],["mediawiki.ui.anchor","0z3ug3d"],["mediawiki.ui.button","1tyjz6g"],["mediawiki.ui.input","1h6ku81"],[
"mediawiki.ui.icon","04kpysq"],["mediawiki.ui.text","18gxip6"],["mediawiki.widgets","0dk1jya",[22,37,139,98,246,267]],["mediawiki.widgets.styles","0v7bxst"],["mediawiki.widgets.DateInputWidget","18rh49u",[92,267]],["mediawiki.widgets.datetime","1y9meuf",[265]],["mediawiki.widgets.CategorySelector","01prjnq",[250]],["mediawiki.widgets.CategoryMultiselectWidget","0vzvpz9",[116,139,267]],["mediawiki.widgets.SelectWithInputWidget","07zyuom",[252,267]],["mediawiki.widgets.SelectWithInputWidget.styles","03xks9j"],["mediawiki.widgets.MediaSearch","1e76xcw",[116,139,267]],["mediawiki.widgets.UserInputWidget","0domuv6",[267]],["mediawiki.widgets.UsersMultiselectWidget","19i28yy",[267]],["mediawiki.widgets.SearchInputWidget","1b0vxib",[136,245]],["mediawiki.widgets.SearchInputWidget.styles","0rksomb"],["mediawiki.widgets.StashedFileWidget","1hfiknt",[265]],["es5-shim","01prjnq"],["dom-level2-shim","01prjnq"],["oojs","189n6dm"],["mediawiki.router","065qjdp",[263]],["oojs-router","0tu3san",[261]],
["oojs-ui","01prjnq",[268,267,269]],["oojs-ui-core","13a4h77",[174,261,266,270,271,272]],["oojs-ui-core.styles","1rd4oz7"],["oojs-ui-widgets","1kl0v73",[265]],["oojs-ui-toolbars","0sn4whw",[265]],["oojs-ui-windows","1h3zqeh",[265]],["oojs-ui.styles.icons","03ft99j"],["oojs-ui.styles.indicators","1v0c0qn"],["oojs-ui.styles.textures","18pz28k"],["oojs-ui.styles.icons-accessibility","0nv3tqi"],["oojs-ui.styles.icons-alerts","1ppzw61"],["oojs-ui.styles.icons-content","0pxz8dv"],["oojs-ui.styles.icons-editing-advanced","02c35ga"],["oojs-ui.styles.icons-editing-core","0q4dk7j"],["oojs-ui.styles.icons-editing-list","06aqcku"],["oojs-ui.styles.icons-editing-styling","0ov8aaa"],["oojs-ui.styles.icons-interactions","123chk4"],["oojs-ui.styles.icons-layout","1fekzck"],["oojs-ui.styles.icons-location","1qsqmkt"],["oojs-ui.styles.icons-media","1mdzok4"],["oojs-ui.styles.icons-moderation","1pfbzkj"],["oojs-ui.styles.icons-movement","0zd22ef"],["oojs-ui.styles.icons-user","17gx3lp"],[
"oojs-ui.styles.icons-wikimedia","13kk1bs"],["ext.math.styles","0jn1b76"],["ext.math.scripts","1tr7ybd"],["ext.math.editbutton.enabler","0f0l747"],["ext.math.visualEditor","0s1ya0e",[288,"ext.visualEditor.mwcore"]],["ext.math.visualEditor.mathSymbolsData","1lhfai2",[291]],["ext.math.visualEditor.mathSymbols","073fi6w",[292]],["ext.math.visualEditor.chemSymbolsData","1bxfz6z",[291]],["ext.math.visualEditor.chemSymbols","0xtkdgu",[294]],["skins.vector.styles","1tnigxk"],["skins.vector.styles.responsive","1t0b563"],["skins.vector.js","1pujsgh",[50,53]],["ext.nuke","11p6h1t"],["ext.abuseFilter","1ezr54y"],["ext.abuseFilter.edit","0gajaaz",[47,52,98]],["ext.abuseFilter.tools","1mdm4h4",[47,98]],["ext.abuseFilter.examine","1p76bhh",[47,98]],["ext.geshi.local","0301upp"],["ext.interwiki.specialpage","0yevebq",[41]],["ext.cite","1ydj85b"],["ext.rtlcite","0wfo1xh"],["ext.vector.collapsibleNav","0kasmo5",[29,50,151]],["ext.vector.expandableSearch","190hql6",[25,"jquery.delayedBind",30]],[
"ext.vector.sectionEditLinks","0eahvrg",[29]],["jquery.wikiEditor","1owks74",[52],"ext.wikiEditor"],["jquery.wikiEditor.dialogs","0yqjzd2",[50,62,317],"ext.wikiEditor"],["jquery.wikiEditor.dialogs.config","0otiet2",[49,312,139,178],"ext.wikiEditor"],["jquery.wikiEditor.preview","01iyhiz",[311],"ext.wikiEditor"],["jquery.wikiEditor.previewDialog","12czqr8",[312],"ext.wikiEditor"],["jquery.wikiEditor.publish","0aflses",[312],"ext.wikiEditor"],["jquery.wikiEditor.toolbar","1lhwq8p",[18,29,311,319],"ext.wikiEditor"],["jquery.wikiEditor.toolbar.config","1ubkls2",[317],"ext.wikiEditor"],["jquery.wikiEditor.toolbar.i18n","1c7r6if",[],"ext.wikiEditor"],["ext.wikiEditor","0xo1p0w",[311],"ext.wikiEditor"],["ext.wikiEditor.dialogs","1yzp9ws",[326,313],"ext.wikiEditor"],["ext.wikiEditor.preview","0o9s6g5",[320,314],"ext.wikiEditor"],["ext.wikiEditor.previewDialog","0xfmeiz",[320,315],"ext.wikiEditor"],["ext.wikiEditor.publish","1o11pxn",[320,316],"ext.wikiEditor"],["ext.wikiEditor.tests.toolbar",
"0y55vi4",[326],"ext.wikiEditor"],["ext.wikiEditor.toolbar","1ghvvys",[320,318],"ext.wikiEditor"],["ext.wikiEditor.toolbar.styles","1oolwqz",[],"ext.wikiEditor"],["ext.wikiEditor.toolbar.hideSig","13magb8",[],"ext.wikiEditor"],["ext.categoryTree","05dno93"],["ext.categoryTree.css","1gvcu5k"]]);;mw.config.set({"wgLoadScript":"/load.php","debug":!1,"skin":"vector","stylepath":"/skins","wgUrlProtocols":"bitcoin\\:|ftp\\:\\/\\/|ftps\\:\\/\\/|geo\\:|git\\:\\/\\/|gopher\\:\\/\\/|http\\:\\/\\/|https\\:\\/\\/|irc\\:\\/\\/|ircs\\:\\/\\/|magnet\\:|mailto\\:|mms\\:\\/\\/|news\\:|nntp\\:\\/\\/|redis\\:\\/\\/|sftp\\:\\/\\/|sip\\:|sips\\:|sms\\:|ssh\\:\\/\\/|svn\\:\\/\\/|tel\\:|telnet\\:\\/\\/|urn\\:|worldwind\\:\\/\\/|xmpp\\:|\\/\\/","wgArticlePath":"/wiki/$1","wgScriptPath":"","wgScriptExtension":".php","wgScript":"/index.php","wgSearchType":null,"wgVariantArticlePath":!1,"wgActionPaths":{},"wgServer":"https://wiki.kerbalspaceprogram.com","wgServerName":"wiki.kerbalspaceprogram.com",
"wgUserLanguage":"en","wgContentLanguage":"en","wgTranslateNumerals":!0,"wgVersion":"1.29.0","wgEnableAPI":!1,"wgEnableWriteAPI":!0,"wgMainPageTitle":"Main Page","wgFormattedNamespaces":{"-2":"Media","-1":"Special","0":"","1":"Talk","2":"User","3":"User talk","4":"Kerbal Space Program Wiki","5":"Kerbal Space Program Wiki talk","6":"File","7":"File talk","8":"MediaWiki","9":"MediaWiki talk","10":"Template","11":"Template talk","12":"Help","13":"Help talk","14":"Category","15":"Category talk","110":"API","111":"API talk"},"wgNamespaceIds":{"media":-2,"special":-1,"":0,"talk":1,"user":2,"user_talk":3,"kerbal_space_program_wiki":4,"kerbal_space_program_wiki_talk":5,"file":6,"file_talk":7,"mediawiki":8,"mediawiki_talk":9,"template":10,"template_talk":11,"help":12,"help_talk":13,"category":14,"category_talk":15,"api":110,"api_talk":111,"image":6,"image_talk":7,"project":4,"project_talk":5},"wgContentNamespaces":[0,110],"wgSiteName":"Kerbal Space Program Wiki","wgDBname":
"kerbalsp_wiki","wgExtraSignatureNamespaces":[],"wgAvailableSkins":{"vector":"Vector","fallback":"Fallback","apioutput":"ApiOutput"},"wgExtensionAssetsPath":"/extensions","wgCookiePrefix":"kerbalsp_wiki_ksp_","wgCookieDomain":"","wgCookiePath":"/","wgCookieExpiration":2592000,"wgResourceLoaderMaxQueryLength":512,"wgCaseSensitiveNamespaces":[],"wgLegalTitleChars":" %!\"$&'()*,\\-./0-9:;=?@A-Z\\\\\\^_`a-z~+\\u0080-\\uFFFF","wgIllegalFileChars":":/\\\\","wgResourceLoaderStorageVersion":1,"wgResourceLoaderStorageEnabled":!0,"wgForeignUploadTargets":["local"],"wgEnableUploads":!0,"wgCollapsibleNavBucketTest":!1,"wgCollapsibleNavForceNewVersion":!1,"wgWikiEditorMagicWords":{"redirect":"#REDIRECT","img_right":"right","img_left":"left","img_none":"none","img_center":"center","img_thumbnail":"thumb","img_framed":"frame","img_frameless":"frameless"}});var RLQ=window.RLQ||[];while(RLQ.length){RLQ.shift()();}window.RLQ={push:function(fn){fn();}};window.NORLQ={push:function(){}};}
mediaWikiLoadStart=mwNow();mwPerformance.mark('mwLoadStart');script=document.createElement('script');script.src="/load.php?debug=false&lang=en&modules=jquery%2Cmediawiki&only=scripts&skin=vector&version=10nipy6";script.onload=script.onreadystatechange=function(){if(!script.readyState||/loaded|complete/.test(script.readyState)){script.onload=script.onreadystatechange=null;script=null;startUp();}};document.getElementsByTagName('head')[0].appendChild(script);}());