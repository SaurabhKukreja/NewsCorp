package com.kukroid.newscorp.presenter;

import com.kukroid.newscorp.Interface.HomeFragmentContract;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by kukresa on 12/30/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeFragmentPresenterTest {

    @Mock
    private HomeFragmentContract view;
    private HomeFragmentPresenter presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new HomeFragmentPresenter(view);
    }

    @Test
    public void testOfflineFunctionality()throws Exception{

        Mockito.when(view.checkNetworkAvailability()).thenReturn(true);
        Mockito.verify(view,Mockito.never()).onOffline();
    }

    @Test
    public void testSwipeToRefresh() throws Exception {

        Mockito.when(view.checkNetworkAvailability()).thenReturn(true);
        presenter.swipeToRefresh();
        Mockito.verify(view).refreshOnSwipe();

    }
    @Test
    public void testSwipeToRefresh_NoNetwork() throws Exception {

        Mockito.when(view.checkNetworkAvailability()).thenReturn(false);
        presenter.swipeToRefresh();
        Mockito.verify(view).onOffline();

    }


}