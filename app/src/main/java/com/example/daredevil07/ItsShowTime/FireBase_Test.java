package com.example.daredevil07.ItsShowTime;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// import org.junit.Before;
//        import org.junit.Test;
//        import org.junit.runner.RunWith;
//        import org.mockito.Mockito;
//        import org.mockito.invocation.InvocationOnMock;
//        import org.mockito.stubbing.Answer;
//        import org.powermock.api.mockito.PowerMockito;
//        import org.powermock.core.classloader.annotations.PrepareForTest;
//        import org.powermock.modules.junit4.PowerMockRunner;
//
//        import static org.mockito.Matchers.any;
//        import static org.mockito.Matchers.anyString;
//        import static org.mockito.Mockito.doAnswer;
//        import static org.mockito.Mockito.when;
//
//public class FireBase_Test {
//
//    private DatabaseReference mockedDatabaseReference;
//
//    public void before() {
//        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);
//
//        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
//        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);
//
//        PowerMockito.mockStatic(FirebaseDatabase.class);
//        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
//    }
//
//    @Test
//    public void getSignedInUserProfileTest() {
//        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);
//
//        doAnswer(new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];
//
//                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
//                //when(mockedDataSnapshot.getValue(User.class)).thenReturn(testOrMockedUser)
//
//                valueEventListener.onDataChange(mockedDataSnapshot);
//                //valueEventListener.onCancelled(...);
//
//                return null;
//            }
//        }).when(mockedDatabaseReference).addListenerForSingleValueEvent(any(ValueEventListener.class));
//
//        new LoginActivity().getSignedInUserProfile();
//
//        // check preferences are updated
//    }
//
//}
