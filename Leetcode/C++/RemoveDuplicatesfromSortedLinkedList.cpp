/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        if (head == nullptr || head->next == nullptr) {
            return head;
        }

        ListNode* current = head;
        ListNode* runner = head->next;

        while (current != nullptr) {
            while (runner != nullptr && runner->val == current->val) {
                runner = runner->next;
            }

            current->next = runner;
            current = current->next;
        }

        return head;
    }
};