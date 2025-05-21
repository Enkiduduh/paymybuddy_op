package com.project.PayMyBuddy;

import com.project.PayMyBuddy.model.Transaction;
import com.project.PayMyBuddy.model.UserConnection;
import com.project.PayMyBuddy.repository.TransactionRepository;
import com.project.PayMyBuddy.repository.UserConnectionRepository;
import com.project.PayMyBuddy.repository.UserRepository;
import com.project.PayMyBuddy.service.ProfilService;
import com.project.PayMyBuddy.service.TransfertService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TransfertServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    TransfertService transfertService;

    @Mock
    private UserConnectionRepository userConnectionRepository;

    @Mock
    private ProfilService profilService;

    private com.project.PayMyBuddy.model.User userSender;
    private com.project.PayMyBuddy.model.User userReceiver;


    @BeforeEach
    void setup() {
        userSender = new com.project.PayMyBuddy.model.User();
        userSender.setId(42L);
        userSender.setUsername("joe");
        userSender.setEmail("joe@joe.fr");
        userSender.setPassword("joe");

        userReceiver = new com.project.PayMyBuddy.model.User();
        userReceiver.setId(42L);
        userReceiver.setUsername("jack");
        userReceiver.setEmail("jack@jack.fr");
        userReceiver.setPassword("jack");
    }

    @Test
    void executeTransfert_savesTransaction() {
        //1. GIVEN
        // on stub les findById
        when(userRepository.findById(42L)).thenReturn(Optional.of(userSender));
        when(userRepository.findById(56L)).thenReturn(Optional.of(userReceiver));

        //on stub save pour renvoyer l 'instance
        when(transactionRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        double amount = 15.0;
        String description = "payment test";

        //2. WHEN
        Transaction trans = transfertService.executeTransfert(42L, 56L, description, amount);
        //3. THEN
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository).save(captor.capture());

        Transaction saved = captor.getValue();
        assertThat(saved.getSender()).isSameAs(userSender);
        assertThat(saved.getReceiver()).isSameAs(userReceiver);
        assertThat(saved.getAmount()).isEqualTo(amount);
        assertThat(saved.getDescription()).isEqualTo(description);
    }

    @Test
    void executeTransfert_unknownSender_throws() {
        when(userRepository.findById(42L)).thenReturn(Optional.empty());
        assertThatThrownBy(() ->
                transfertService.executeTransfert(42L, 56L, "x", 1.0))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Sender not found");
    }

    @Test
    void findByUserId_returnsAllConnectionsForThatUser() {
        // GIVEN
        UserConnection c1 = new UserConnection();
        UserConnection c2 = new UserConnection();
        List<UserConnection> stubbed = Arrays.asList(c1, c2);
        given(userConnectionRepository.findByUserId(42L))
                .willReturn(stubbed);

        // WHEN
        List<UserConnection> result = transfertService.findByUserId(42L);

        // THEN
        assertThat(result).isSameAs(stubbed);
    }
}
