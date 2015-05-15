from userauth.views import LoginView, LogoutView

urlpatterns = patterns(
    url(r'^api/auth/login/$', LoginView.as_view(), name='login'),
	 url(r'^api/auth/logout/$', LogoutView.as_view(), name='logout'),
 
)